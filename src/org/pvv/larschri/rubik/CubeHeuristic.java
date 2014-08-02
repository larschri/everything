package org.pvv.larschri.rubik;

import java.util.List;

public class CubeHeuristic {

    private final static PairLookup PAIR_LOOKUP = new PairLookup(false);

    private static class PairLookup {

        private final int[][][] lookup;

        PairLookup(boolean compute) {
            lookup = compute ? compute() : fromSerializedString();
        }

        /**
         * Constructs a lookup array where array[color][corner][edge] is an integer
         * 0-6 which represents the number of moves it takes to move the corner
         * facelet with the given 'color' to the position given by 'corner' and the
         * edge facelet with the given 'color' to the position given by 'edge'.
         */
        private static int[][][] compute() {
            int[][][] lookup = new int[24][24][24];
            // Maximum is 6, and it's much more feasible to initialize it here
            // than attempt to traverse another level with CubeTraverser.
            for (int i = 0; i < lookup.length; i++) {
                for (int j = 0; j < lookup[i].length; j++) {
                    for (int k = 0; k < lookup[i][j].length; k++) {
                        lookup[i][j][k] = 6;
                    }
                }
            }

            List<List<Cube>> results = CubeTraverser.traverse(Cubes.SOLVED, 6);
            for (int level = results.size() - 1; level >= 0; level--) {
                for (Cube cube : results.get(level)) {
                    Cube inv = cube.inverse();
                    List<Integer> corners = inv.getCorners();
                    List<Integer> edges = inv.getEdges();
                    for (int i = 0; i < 24; i++) {
                        lookup[i][corners.get(i)][edges.get(i)] = level;
                    }
                }
            }

            return lookup;
        }

        static int[][][] fromSerializedString() {
            String s = "033454455334434434443334314344553443543445443344231345444443434446442333334144455434334445443443333324454344323344344444122253343344434334343433333345243332444334444444332345434343434345442333324444352443433444343343234334444243323355343444233245444423334444453453333455354441434334444444344234444433234435443343343335554343514444344444223345443443442444454344233344343332434234553454233344344442434325453454133434445432323343442333244334444343424445253444232345444443434445432444232346444343434345441434223345343453433444344243122234343442323344352423233234444433335434453352133443455444544443344433403333454443554444343343331444335444544545433443323144346444445444343332323424345345434433234443223342344454445443344533433344144443555334344444432444324344544344333433413343253444434433233332324434345254434444244443323244345434445444344442323344245452444334344543333234345444345434343332333334444344524433234444212234434343453334344333333333234443445234444444323333234553444324344543234443345443434442343433334334344344535545144444322344334454445444423444322345334344445344332434212244234354434333234232223343344453434443353523323234345444446434344341132344464344444344453323413344453344543445444334340344344344533445543433433144455434344355444433432425454244434344344434232343454344444344454424332345254343444234445434341344433233543244343323423444352344443344344333333434445144434355354444332245444424344344454443332355344342333234445434443234444334244335444333432334553233424344344434322345443344442344455334343344344343444135554444333344344443333224454444233344454343434343453323333334443233434445244444221234344343334434533334221235443233344234342324322345343354443344345233232344454343434344463414332234444334345334454342143343455445544433444334313234444454644434443233341334435444544445433344334034445544444345334333433314455355434434344444233242444454445433444434333234244443455323334345442334424344544334433334323334343454544434343233333323335244434444344444222134344533434343343343333344344452444323334444334123334344344425433233443224444344525434344344323234444454543434444244333234344443545224444345334334345553444314444444244333445443434432443334343223334344534534244344332234434454445434423345332235434344445334432335323234344464544434344143233233444453434433453424222123334343435423443242244332444435443433434334424323433444553434443233442323324544444534533344444133345535344444444343533403345445344433344344344331434455454433445434444323134544464423334344543433414445454434433344434433332445443444443233334412225334343434334343333233334524344444444443434333234543454423334343444223334434254534544343543213343444434423333233434324433444452534444244444323234544454324444344434323234644454414344343345322334534443442434334344212223434443524233233443323323444344533523354443334423444354433432344434334333555443444445144344322334544444543444424333223334434345534544342344441334325332333324344525432443434442444434344543432324434443444424454545232334424343445434443544413344345433444335444444340333345443433435544544433144433454334435445644432314434443433324454534532342434332344434344445422334234433445334454444343334414343444445553434443244432443334335443455332333323243445434443544323444334423434334344434433433434451444445355445432234433444234444454434432234533443324344453435421224423332342324343445322334334433535234344544432323434343443414464544433323434343433323454434433333444332344445244434321223443343443334533444333333323344444444452234442344435443443334433514433343444553544444343442433224544444544433443434233235534344454343332434413234446444533234443334441334445454443345434434434034434455434335334543443314445554444333443424443242545443444344343434423234345444544244443434333234525344454344442323334134443443433235432444333334434244544443332434323334445434533234343323333333444452444444344434322123434345333343344323322123544343423243442335432234534443452334433434323234445444634144343433433223444344543423453433444323444354443332443323343233455443444344243334432234544444553344423434334334434355544444441345432333434343432335444524433332333443444444344453322213434433433434343445233334434233344444443544514334345334443345444445431323444344432336444544434133443454333445444554433403444453343334443535543331445343444444344445423324244334444344454444333323424233343454553434444233442344333345443555333433434144444444443544324433344324433344344434434322333342443445345445433223443344233454454434433223543344323354453446432323434343441435444445323323344334534244344434322212333234432424354434433412333254332333444434444322444343443445254445432323444344442445434444333323434244443455452244543443333444432334434533433441222343343433434452433323333444444433444454343433323233343434544443524433244334343344434344442432343344432335534454444232332345333444445553544413334444443433444544553340334333443443444445534433143334454344544454444432313233343444644444554343341344333444544464443432323143443434544453434532233424343344434343434421222242332334435344444332332335233543445344444333442334323443544355543433433444451444434454434432233434444244445443433322333345443423455443444422333345443432545344454321334233332334344344443432443344442444525454444432323244443444543243453453234444343443323423444542233453344544334441444434333444455533434443243444324343354434433432534444133333243443323343452543244444343444424443454343232444244544434442454523233454344433434434554441334443354444334334544434033334355444434443354443314344354454543443464443231333244544434453343443223243444534433442343542122423243433323433444532233352343444335343454443232434144643434343454443332333234543434344443443333444452443323344343432122433345333434332344433333444444523444332345533233454344432434433454432344343343444234343443443343444453554514443344543223344444544442254542444324443443434434434543442323442444434445452543433323543444423444444332333413332354324434443523444234433344334434344451443334444443435535454444243322444334434445553443423323543433323444444643441323332344434445444533444133433454344544443443443403343353344554444554344331443334435544354432332212232434423434453433543223523344334434444543432323341443434446344443343322434234533445344443344432433324433544345532334323443442434434454433443223533444234445443443433433444444413555443444433333444433322445444543432333332343434345344432333333444443444524343443432212333433443453144553554333444443443434424444542332443444543344342444433332434545532333344243444423333454433443343434543233323354443434233352443333444443444434343445332221334343434334443444523333444444432333434554451433433454443344344444543132323364443444344354443413334454444543344455443340433344434533354343443322233544533443343444643232414354443434334444532332342443443345233343432221324243542344233343443341323334442543244443444432434452543434344444543232424454343444343444433332434554522444343455533343444444431444334454432443333443443244233343443432434453453424344344543322334544543442244543444444333332334434434543432333332343434445452433324444333344433444345343433433122244333434444524444344223343344544344442433454233332335534443444233343344233444435553544514444343343433444444643431434232343434445344534434243223353343444343433322423122242343544443454333352233233444534455444433334033434534434554434543344314343344445444544432333231343444446454444333443334134544445444544333453233223444544355543434444333441444434354434433343324443243444443433323444234343423455443444423444244343432545444544432444232343444345344443433454233342444525443433322333133432544443144453554444433343443434324443543433432444433344342444434443323445532333344254444533223354443443433433454333212243433434233352444444333343444434343454343332333244543434443444524444333344432333334553442434322344433344234443434232212254432333344344433523223353443543343464444341323244543434454344544433133444543443453344553343403343443444334445543443331444544345344454443332323144643444344454444442323234543444244443444543323352543434254344433332413344342333343444434443324454522444343455534444334344431445334454443444322354444244233343444543323353453424344344433433234443543442254542445434332343434434434432543323341333323444452543434434432444423444454343444424232344434544444523445334322344334544344441444444343343435535443443244333443234434435553443424434432333323444443542342324221233323434344533445233322354333444454443433414232343434644443453344342332234434534464443443323132344434544454434544334413344334445344434533433340344435445454443344433433134544455444543344443332224444544345532335434332342434434354433444333423444233444443443434444333444513555443444434444333333322445343444333334221243435334344432334444333343444524454443433323233343434543243453454345333243443323423443543334442344434334451444434444433355533434444254444434233254444433443434544244323254444434343452544345333243444424332344343233334144434325442454524344443244433434433553442335332244434334343444544143323264443434443344433424233253444533332354433242222143434423454344544334143345544433443444643233313254444434433444543344341344544345443443444333334044553345343443433343222133453443344443444444333352443323343444543233323354343434332344434444333344523444332345534344343244432434433454443345332254444234343443444444334353554414443344433334244343544432244443444445454422334334424334543444553423333233442333434434443534423344445144445535344434334343434314344446444523234343344342433445344422335334333224233434354412224234543333524434453423323344434444442445443433333233434323334345444533234343333244444524344433334443434334333453343412224433444234444434254524434343444324444445434523234344434334543444452523334244333223334434444313343254444333344554443403343453345433445544444531434334444323334445444623134344443334434544444533413454443334534445454423322344434344443555443433344144344333433544344432444324333234444434345523434342334543334334343421224343524444442333443433334344543433323434343433324454445244444434233333334443534424343345334432234443434342322344233321225443444335233443354322335344644443413434343432324454535544441444343443334344435434333244334443244443444344433424233332344553544445333442344322335444555344443434144533434443544434443344424432235444434445432333342432335345444334333443344223444354445444334543344313344454445533434533344440334344455434433344434533144454544433323444344432314464544444423444344432323454434445432444343432335254444333322543233341334434444344433434244432445452234453344445454432234433414444443444553534334343432443334434443544323443434244345534344443233332423423244435343422123332334452333445344432235433434334144544464423234343533443424434453433223443424454342545443433234343325433234344344434133332434344344525344443244442434444244543454423234443444344444434244533333332443333343434533422124343323344443444452433334344434333234544454323334343434433234644454413234443345443344544444541334433345334333444544534034443433444334544445543313454433444434445454433222444323354343455443433234243334443333544344442344423434344444434355533344451345442444434443432325444525443453434442433324344443432333323432533414443545243444424343444324443534423354335433433224443445441433434343432326444444334244433453323325344544332423323442322214343534543452434332333324344435433344234433444234443444344444514343443335553544444344442443323325444455343443323243434324443544433454334423433225444434444443434441433435355444333344433443224434354445443344543443314334554446432334434443431325444445433444334434534134454434443334434334533404455434333433434344322213345434444443444332333335244445432333434343432335434444344443323344433334452143444464343434344452323424334453443533434442233242334343332423435441222335244345433334445342332444424454344323344343333233343454343434344453323444445243332444334443333343334534343443334341222434444452444433445442233345434444243323355342333334344344423334444353442444455354451434334443433345344454433234445442332444435554343414444343334334335443443432434443244344444343332434234552343344444344442434325452443244444454443434443452323345434444343424445252333233344343332325444431334333445544443345344340334334455443454433444453143233344454443434444462313344345444433345444453341243433455344444333443223423223444343544323332122352334434443534435432233434134346444445434343232444414445355434434344333343332444354444333444324444334244443455323333234453334425444544434432233433343343345434334342122444423335244434444343333333234345434445434343332444444344452444323333333444234445444345434443232454324444344525434343233333225434443443423334133444334344443545224443244444434345553444314453343344433445444544442443223454323334344534534243233343334434443435434422344443345434454445434431334334345334455434434444033344333444554445443453314333234445444446434443231232444354234333234342212523334453344543334443223341445444343434346442323434244345334344345343322543425454244434344343323332343443254333234443413443445254343444234444324442445434344444345442323533444452344443345443223444434444144434355353433433344344324344344354432443455344342333234444323444344454334244445443322543434553233424344343323433335443344442334444234444444344343445135553334444444344443333224453333333434344433434353342212444434443233434445243333332345444343434345432333332346444344444345441323433445443454443344454133343334443453444354453403443345444334345444554331233543355344444343343322414334344454644434343232342444334443534445332332324233235443434344232221434524345345434433233332333442344354444343344423444445144443555334344333443444425444544444332332424444343454544444343232434534345254434444243332323333234434444343253341434444245452444334344432334334344343334534432221444434444344524433233333323334344454543434343233444433234443445234443333434433234553444324343432334543345444544442343322444434344344535544143343333444334443435444322443433445434454455444331433323344344464544444343132334443344454445443453413433344344344445533453340";
            int[][][] lookup = new int[24][24][24];
            int charPos = 0;
            for (int i = 0; i < lookup.length; i++) {
                for (int j = 0; j < lookup[i].length; j++) {
                    for (int k = 0; k < lookup[i][j].length; k++) {
                        lookup[i][j][k] = s.charAt(charPos++) - '0';
                    }
                }
            }
            return lookup;
        }

        public String asSerializedString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < lookup.length; i++) {
                for (int j = 0; j < lookup[i].length; j++) {
                    for (int k = 0; k < lookup[i][j].length; k++) {
                        sb.append(lookup[i][j][k]);
                    }
                }
            }
            return sb.toString();
        }

        int[] analyze(Cube c) {
            Cube inv = c.inverse();
            List<Integer> corners = inv.getCorners();
            List<Integer> edges = inv.getEdges();
            int[] result = new int[24];
            for (int i = 0; i < result.length; i++) {
                result[i] = lookup[i][corners.get(i)][edges.get(i)];
            }
            return result;
        }
    }

    /**
     * Convert the given cube to a number that is low for organized cubes and
     * higher for more chaotic cubes.
     */
    static int calcHeuristic(Cube cube) {
        int[] analyze = PAIR_LOOKUP.analyze(cube);
        int[] counts = new int[7];
        for (int j = 0; j < analyze.length; j++) {
            counts[analyze[j]]++;
        }
        int result = 0;
        int[] multipliers = new int[] {0, 1, 10, 100, 200, 200, 200};
        for (int i = 0; i < counts.length; i++) {
            result += counts[i] * multipliers[i];
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new PairLookup(true).asSerializedString().equals(PAIR_LOOKUP.asSerializedString()));
    }
}