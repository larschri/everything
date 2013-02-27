package org.pvv.larschri.gameday;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.pvv.larschri.gameday.xml.Grid;
import org.pvv.larschri.gameday.xml.Grid.Game;
import org.pvv.larschri.gameday.xml.InningAll;
import org.pvv.larschri.gameday.xml.Players;

/**
 * Class to download and construct objects. The downloaded content are stored
 * locally, and will not be downloaded again. This class will never update data
 * stored locally.
 */
public class Downloader {
	private final Path downloads;
	private final URL remote;

	/** Local path to stored downloaded content. Remote base url to retrieve content. */
	public Downloader(Path downloads, URL remote) {
		if (!downloads.toFile().isDirectory())
			throw new IllegalArgumentException(downloads + " is not a directory");
		this.downloads = downloads;
		this.remote = remote;
	}

	private Path resolve(int year, int month, int day) {
		return downloads.resolve(String.format("components/game/mlb/year_%d/month_%02d/day_%02d", year, month, day));
	}

	/** Converts the absolute path to an url constructing a path that is relative to {@link #downloads} */
	private URL getURL(Path path) {
		try {
			return new URL(remote, downloads.relativize(path).toString());
		} catch (MalformedURLException e) {
			throw new RuntimeException("not a valid url spec: " + path);
		}
	}

	private void download(Path path) throws IOException {
		URL src = getURL(path);
		path.getParent().toFile().mkdirs();
		try (
				ReadableByteChannel rbc = Channels.newChannel(src.openStream());
				FileOutputStream fos = new FileOutputStream(path.toFile()))
		{
			fos.getChannel().transferFrom(rbc, 0, 1 << 24);
		}
	}

	private <X> X get(Class<X> clazz, Path path) throws IOException, JAXBException {
		if (!path.toFile().exists())
			download(path);

		JAXBContext ctx = JAXBContext.newInstance(new Class[] {clazz});
		Unmarshaller um = ctx.createUnmarshaller();
		@SuppressWarnings("unchecked") X x = (X) um.unmarshal(path.toFile());
		return x;
	}

	private static final Pattern GRID_GAME_ID_PATTERN = Pattern.compile("(\\d{4})/(\\d{2})/(\\d{2})/(.*)");

	private Path gamePath(Grid.Game game) {
		Matcher matcher = GRID_GAME_ID_PATTERN.matcher(game.id);
		if (!matcher.matches())
			throw new IllegalArgumentException("Could not parse game id: " + game.id);

		int year = Integer.parseInt(matcher.group(1));
		int month = Integer.parseInt(matcher.group(2));
		int day = Integer.parseInt(matcher.group(3));

		return resolve(year, month, day).resolve("gid_" + game.id.replace('/', '_').replace('-', '_'));
	}

	/** retrieve and return {@link Players} */
	public Players players(Grid.Game game) throws IOException, JAXBException {
		return get(Players.class, gamePath(game).resolve("players.xml"));
	}

	/** retrieve and return {@link InningAll} */
	public InningAll inningAll(Grid.Game game) throws IOException, JAXBException {
		return get(InningAll.class, gamePath(game).resolve("inning/inning_all.xml"));
	}

	/** retrieve and return {@link Grid} */
	public Grid grid(int year, int month, int day) throws IOException, JAXBException {
		return get(Grid.class, resolve(year, month, day).resolve("grid.xml"));
	}

	/** Test stuff */
	public static void main(String[] args) throws NumberFormatException, IOException, JAXBException {
		Downloader downloader = new Downloader(Paths.get(args[0]), new URL("http://gd2.mlb.com"));
		for (GregorianCalendar calendar = new GregorianCalendar(2012, Calendar.JANUARY, 1);
				calendar.get(Calendar.YEAR) < 2013;
				calendar.add(Calendar.DATE, 1))
		{
			Grid grid = downloader.grid(calendar.get(Calendar.YEAR), 1 + calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
			for (Game game : grid.game) {
				if (game.venue.equals(("AT&T Park"))) {
					System.err.println(game.awayTeamName + " " + game.id);
					downloader.players(game);
					downloader.inningAll(game);
				}
			}
		}
	}
}
