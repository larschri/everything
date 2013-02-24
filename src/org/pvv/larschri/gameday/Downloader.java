package org.pvv.larschri.gameday;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.pvv.larschri.gameday.xml.Grid;
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

		String year = matcher.group(1);
		String month = matcher.group(2);
		String day = matcher.group(3);

		return resolve(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day))
				.resolve("gid_" + game.id.replace('/', '_').replace('-', '_'));
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
		Grid grid = downloader.grid(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
		System.err.println(grid.game.get(14).venue);
		System.err.println(downloader.getURL(Paths.get("/Users/larschri/.gameday/components/game/mlb/year_2013/month_06/day_13/grid.xml")));
		System.err.println(downloader.players(grid.game.get(14)));
		System.err.println(downloader.inningAll(grid.game.get(14)));
	}
}
