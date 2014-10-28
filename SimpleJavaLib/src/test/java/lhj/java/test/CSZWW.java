package lhj.java.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.regex.Pattern;

public class CSZWW {
	public static final String DIR = "F:/Sealion/books/jpnx/";

	static final String[] files = { "2695397.html", "2696719.html",
			"2700023.html", "2700495.html", "2702316.html", "2702750.html",
			"2706257.html", "2707177.html", "2708590.html", "2708947.html",
			"2710309.html", "2710630.html", "2731961.html", "2732528.html",
			"2734157.html", "2734617.html", "2739387.html", "2739904.html",
			"2779639.html", "2780236.html", "2816567.html", "2816568.html",
			"2816569.html", "2816570.html", "2816571.html", "2816573.html",
			"2816574.html", "2816575.html", "2816577.html", "2816578.html",
			"2816579.html", "2816580.html", "2816581.html", "2816582.html",
			"2816585.html", "2816586.html", "2816587.html", "2816589.html",
			"2824630.html", "2825181.html", "2826638.html", "2827734.html",
			"2829113.html", "2829441.html", "2831077.html", "2832028.html",
			"2832236.html", "2835078.html", "2836110.html", "2836118.html",
			"2839050.html", "2841617.html", "2841618.html", "2843427.html",
			"2843772.html", "2844627.html", "4066981.html", "2845880.html",
			"2847160.html", "2847823.html", "2848200.html", "2848876.html",
			"4066982.html", "4066983.html", "2850713.html", "4066984.html",
			"3798937.html", "4066985.html", "4066986.html", "2859534.html",
			"2867862.html", "2869690.html", "2870385.html", "2871062.html",
			"2872073.html", "2872644.html", "2888067.html", "2893459.html",
			"2901479.html", "2905750.html", "2928066.html", "2928652.html",
			"2951893.html", "2956812.html", "2982596.html", "2983039.html",
			"3008080.html", "3008107.html", "3008594.html", "3008623.html",
			"3008713.html", "3008743.html", "3008826.html", "3008848.html",
			"3016476.html", "3020886.html", "3061459.html", "3066439.html",
			"3104150.html", "3106945.html", "3135121.html", "3139155.html",
			"3154133.html", "3155193.html", "3166787.html", "3167496.html",
			"3171120.html", "3171914.html", "3175166.html", "3175749.html",
			"3177949.html", "3178646.html", "3184999.html", "3185749.html",
			"3188573.html", "3189162.html", "3191501.html", "3192249.html",
			"3203501.html", "3204255.html", "3212729.html", "3213363.html",
			"3218921.html", "3219593.html", "3221899.html", "3222472.html",
			"3228026.html", "3228679.html", "3234027.html", "3234303.html",
			"3234683.html", "3235073.html", "3239376.html", "3239773.html",
			"3240190.html", "3240638.html", "3244253.html", "3244510.html",
			"3244950.html", "3245373.html", "3250862.html", "3252649.html",
			"3252961.html", "3253326.html", "3255335.html", "3255637.html",
			"3256039.html", "3256469.html", "3260202.html", "3260906.html",
			"3263145.html", "3263826.html", "3266523.html", "3267380.html",
			"3269511.html", "3270273.html", "3273812.html", "3274306.html",
			"3276944.html", "3277538.html", "3279971.html", "3280537.html",
			"3282776.html", "3283419.html", "3285957.html", "3286633.html",
			"3288928.html", "3289563.html", "3302513.html", "3310961.html",
			"3348176.html", "3348766.html", "3351142.html", "3352003.html",
			"3354622.html", "3355127.html", "3357850.html", "3358852.html",
			"3360494.html", "3361311.html", "3363999.html", "3364412.html",
			"3385463.html", "3390512.html", "3401550.html", "3402143.html",
			"3404181.html", "3404668.html", "3406788.html", "3407314.html",
			"3409110.html", "3409410.html", "3411094.html", "3411741.html",
			"3414792.html", "3415531.html", "3418004.html", "3418644.html",
			"3436179.html", "3447485.html", "3450463.html", "3452428.html",
			"3453107.html", "3453754.html", "3454534.html", "3454535.html",
			"3454536.html", "3455378.html", "3456508.html", "3456806.html",
			"3457736.html", "3458783.html", "3459416.html", "3460945.html",
			"3461830.html", "3462256.html", "3463366.html", "3464230.html",
			"3464841.html", "3465328.html", "3465877.html", "3466853.html",
			"3473425.html", "3475549.html", "3476219.html", "3476774.html",
			"3478595.html", "3479015.html", "3479490.html", "3481109.html",
			"3481448.html", "3481727.html", "3483276.html", "3483619.html",
			"3483911.html", "3485698.html", "3486008.html", "3486274.html",
			"3487999.html", "3488281.html", "3488501.html", "3489864.html",
			"3489941.html", "3490123.html", "3490174.html", "3490342.html",
			"3490408.html", "3498232.html", "3498765.html", "3510624.html",
			"3511251.html", "3513641.html", "3514171.html", "3515947.html",
			"3516339.html", "3518853.html", "3519695.html", "3521510.html",
			"3522042.html", "3523838.html", "3524253.html", "3526535.html",
			"3526612.html", "3526763.html", "3526843.html", "3529381.html",
			"3530349.html", "3532628.html", "3533511.html", "3536326.html",
			"3536848.html", "3539490.html", "3540012.html", "3541372.html",
			"3541868.html", "3543331.html", "3543856.html", "3544346.html",
			"3544880.html", "3547519.html", "3547995.html", "3549461.html",
			"3549918.html", "3551425.html", "3551916.html", "3553824.html",
			"3554267.html", "3555214.html", "3555838.html", "3556212.html",
			"3557854.html", "3558373.html", "3560127.html", "3560729.html",
			"3569327.html", "3570177.html", "3570598.html", "3571793.html",
			"3572318.html", "3573827.html", "3574277.html", "3574990.html",
			"3576027.html", "3576395.html", "3577806.html", "3578367.html",
			"3579536.html", "3579919.html", "3588274.html", "3588761.html",
			"3592190.html", "3592519.html", "3594036.html", "3594551.html",
			"3595671.html", "3596025.html", "3597072.html", "3597377.html",
			"3598682.html", "3598975.html", "3599993.html", "3600319.html",
			"3601177.html", "3601530.html", "3602411.html", "3602667.html",
			"3603920.html", "3604274.html", "3605329.html", "3605611.html",
			"3606584.html", "3606902.html", "3607786.html", "3608114.html",
			"3609133.html", "3609474.html", "3640213.html", "3640900.html",
			"3642535.html", "3643058.html", "3644330.html", "3644846.html",
			"3654835.html", "3655433.html", "3657013.html", "3657601.html",
			"3658904.html", "3659526.html", "3662918.html", "3663395.html",
			"3664663.html", "3666166.html", "3666541.html", "3667619.html",
			"3668049.html", "3681658.html", "3682370.html", "3683105.html",
			"3684033.html", "3684563.html", "3685626.html", "3686275.html",
			"3686777.html", "3707043.html", "3710184.html", "3710687.html",
			"3711930.html", "3712469.html", "3713256.html", "3714670.html",
			"3714970.html", "3715387.html", "3717098.html", "3717677.html",
			"3719224.html", "3719937.html", "3721986.html", "3722597.html",
			"3724375.html", "3724873.html", "3726210.html", "3726702.html",
			"3740509.html", "3741067.html", "3742712.html", "3743300.html",
			"3748041.html", "3748417.html", "3749297.html", "3749720.html",
			"3749982.html", "3750493.html", "3751953.html", "3752491.html",
			"3753875.html", "3754386.html", "3755583.html", "3756022.html",
			"3757677.html", "3758287.html", "3759498.html", "3759874.html",
			"3760822.html", "3761307.html", "3762653.html", "3762762.html",
			"3763120.html", "3763224.html", "3765461.html", "3766468.html",
			"3767989.html", "3768821.html", "3775694.html", "3776272.html",
			"3779549.html", "3780086.html", "3781348.html", "3781771.html",
			"3783179.html", "3783691.html", "3785007.html", "3785517.html",
			"3786934.html", "3787482.html", "3788791.html", "3789249.html",
			"3790451.html", "3790878.html", "3791952.html", "3792360.html",
			"3793441.html", "3793762.html", "3795216.html", "3795610.html",
			"3796805.html", "3797280.html", "3798499.html", "3798671.html",
			"3798923.html", "3804513.html", "3804900.html", "3805800.html",
			"3806203.html", "3807160.html", "3807521.html", "3808603.html",
			"3808702.html", "3808827.html", "3809781.html", "3810367.html",
			"3812340.html", "3812823.html", "3835546.html", "3835592.html",
			"3835633.html", "3835634.html", "3835635.html", "3835668.html",
			"3835708.html", "3883340.html", "3883341.html", "3883419.html",
			"3896316.html", "3896317.html", "3899014.html", "3924428.html",
			"3924429.html", "3940297.html", "3940298.html", "3948880.html",
			"3956972.html", "3956979.html", "3956984.html", "4008216.html",
			"3966049.html", "3973002.html", "3974417.html", "3983725.html",
			"3996860.html", "3996869.html", "3996870.html", "3996871.html",
			"4006187.html", "4006176.html", "4006178.html", "4006181.html",
			"4006912.html", "4007931.html", "4012933.html", "4015410.html",
			"4017288.html", "4030601.html", "4030602.html", "4030776.html",
			"4030777.html", "4030779.html", "4030780.html", "4031379.html",
			"4036779.html", "4037532.html", "4040399.html", "4040656.html",
			"4045190.html", "4045533.html", "4047974.html", "4048225.html",
			"4050991.html", "4051242.html", "4053600.html", "4054233.html",
			"4055574.html", "4058581.html", "4062215.html", "4061442.html",
			"4064118.html", "4064853.html", "4067000.html", "4068973.html",
			"4071144.html", "4071145.html", "4074725.html", "4075105.html",
			"4081167.html", "4081520.html", "4086476.html", "4087700.html",
			"4091378.html", "4092416.html", "4098499.html", "4099194.html",
			"4101661.html", "4102095.html", "4104409.html", "4105064.html",
			"4107038.html", "4107552.html", "4109693.html", "4110072.html",
			"4112175.html", "4114389.html", "4115592.html", "4115608.html",
			"4117064.html", "4117449.html", "4121198.html", "4123031.html",
			"4124946.html", "4125938.html", "4128035.html", "4128389.html",
			"4133634.html", "4134162.html", "4139483.html", "4140532.html",
			"4143650.html", "4144133.html", "4145740.html", "4146270.html",
			"4151656.html", "4152369.html", "4160733.html", "4164086.html",
			"4171002.html", "4172449.html", "4178801.html", "4180251.html",
			"4190922.html", "4192691.html", "4198683.html", "4199320.html",
			"4202763.html", "4205082.html", "4207430.html", "4208114.html",
			"4209901.html", "4210131.html", "4210556.html", "4212594.html",
			"4212763.html", "4214155.html", "4219265.html", "4219564.html",
			"4219903.html", "4222365.html", "4222476.html", "4223001.html",
			"4225288.html", "4226317.html", "4228297.html", "4228570.html",
			"4228870.html", "4231049.html", "4231701.html", "4233191.html",
			"4233802.html", "4237380.html", "4237923.html", "4239840.html",
			"4240404.html", "4246030.html", "4246663.html", "4262231.html",
			"4289370.html", "4355672.html", "4367983.html", "4384343.html",
			"4386697.html", "4399666.html", "4399667.html", "4404344.html",
			"4405156.html", "4411273.html", "4413798.html", "4418210.html",
			"4422382.html", "4422383.html", "4427079.html", "4437057.html",
			"4438894.html", "4440852.html", "4452856.html", "4505547.html",
			"4517595.html", "4519070.html", "4522641.html", "4654238.html",
			"4683894.html", "4693037.html", "4789812.html", "4790464.html",
			"4825830.html", "4826904.html", "4870940.html", "4871962.html",
			"4921068.html", "4921921.html", "4925344.html", "4925345.html",
			"4928616.html", "4929437.html", "4932400.html", "4932966.html",
			"4936326.html", "4937189.html", "4939728.html", "4940691.html",
			"4957817.html", "4957818.html", "4957819.html", "4957820.html",
			"4957821.html", "4957822.html", "4957823.html", "4957824.html",
			"4963599.html", "4963600.html", "4968047.html", "4969407.html",
			"4975651.html", "4976291.html", "4981753.html", "4981754.html",
			"4989791.html", "4993837.html", "5000133.html", "5000996.html",
			"5006559.html", "5008153.html", "5019436.html", "5020314.html",
			"5024886.html", "5025988.html", "5030798.html", "5033052.html",
			"5048559.html", "5053321.html", "5084270.html", "5089001.html",
			"5135874.html", "5145620.html", "5178578.html", "5182451.html",
			"5214647.html", "5219044.html", "5242558.html", "5247165.html",
			"5277913.html", "5281497.html", "5302586.html", "5306187.html",
			"5326380.html", "5336206.html", "5341805.html", "5344858.html",
			"5359182.html", "5362168.html", "5374974.html", "5377128.html",
			"5394622.html", "5399060.html", "5414494.html", "5416953.html",
			"5432737.html", "5434788.html", "5449128.html", "5451435.html",
			"5464514.html", "5466848.html", "5474581.html", "5475320.html",
			"5484825.html", "5487473.html", "5514075.html", "5517808.html",
			"5562003.html", "5562004.html", "5562005.html", "5562006.html",
			"5576308.html", "5576309.html", "5577813.html", "5579445.html",
			"5588374.html", "5589304.html", "5597493.html", "5600313.html",
			"5613267.html", "5613268.html", "5622384.html", "5622385.html",
			"5630802.html", "5631588.html", "5635816.html", "5635819.html",
			"5636750.html", "5639109.html", "5639110.html", "5639111.html",
			"5639112.html", "5639113.html", "5639114.html", "5639115.html",
			"5646814.html", "5646816.html", "5651247.html", "5661689.html",
			"5664561.html", "5674525.html", "5676612.html", "5682324.html",
			"5683488.html", "5689990.html", "5691342.html", "5700135.html",
			"5702814.html", "5715083.html", "5717512.html", "5730438.html",
			"5732441.html", "5741394.html", "5742487.html", "5747850.html",
			"5748309.html", "5749411.html", "5755415.html", "5756466.html",
			"5760099.html", "5762214.html", "5768414.html", "5769786.html",
			"5774099.html", "5775218.html", "5778870.html", "5779697.html",
			"5780304.html", "5783561.html", "5784653.html", "5787320.html",
			"5792843.html", "5794082.html", "5798970.html", "5799932.html",
			"5806186.html", "5807171.html", "5812716.html", "5813314.html",
			"5817370.html", "5818128.html", "5818668.html", "5824073.html",
			"5825239.html", "5829052.html", "5829847.html", "5834334.html",
			"5835597.html", "5846733.html", "5847343.html", "5853848.html",
			"5855006.html", "5862164.html", "5862993.html", "5869816.html",
			"5870087.html", "5876485.html", "5877574.html", "5882813.html",
			"5883647.html", "5884391.html", "5887877.html", "5888945.html",
			"5893993.html", "5894774.html", "5896358.html", "5904207.html",
			"5905667.html", "5910553.html", "5911358.html", "5919328.html",
			"5920285.html", "5933810.html", "5934631.html", "5938232.html",
			"5938233.html", "5949300.html", "5951802.html", "5961528.html",
			"5963440.html", "5969273.html", "5970687.html", "5979118.html",
			"5980540.html", "5987796.html", "5989230.html", "5996070.html",
			"5997183.html", "6003520.html", "6004880.html", "6010081.html",
			"6010996.html", "6014223.html", "6014468.html", "6014997.html",
			"6015685.html", "6016271.html", "6017706.html", "6018148.html",
			"6018579.html", "6019963.html", "6020814.html", "6021596.html",
			"6022317.html", "6023265.html", "6032857.html", "6034434.html",
			"6035263.html", "6047783.html", "6048962.html", "6064384.html",
			"6066471.html", "6073224.html", "6073875.html", "6080341.html",
			"6081068.html", "6084025.html", "6085088.html", "6089934.html",
			"6090611.html", "6094253.html", "6095014.html", "6095515.html",
			"6095830.html", "6100406.html", "6100424.html", "6103099.html",
			"6109117.html", "6109189.html", "6116558.html", "6116859.html",
			"6119582.html", "6121020.html", "6128097.html", "6128934.html",
			"6132770.html", "6133412.html", "6136385.html", "6137193.html",
			"6141499.html", "6142676.html", "6145526.html", "6146463.html",
			"6155817.html", "6156547.html", "6159278.html", "6160521.html",
			"6163400.html", "6164306.html", "6167213.html", "6167899.html",
			"6172291.html", "6172984.html", "6177096.html", "6177894.html",
			"6181124.html", "6181916.html", "6185294.html", "6186001.html",
			"6189590.html", "6192068.html", "6192680.html", "6197288.html",
			"6197927.html", "6201586.html", "6202704.html", "6206460.html",
			"6208046.html", "6211182.html", "6214861.html", "6232086.html",
			"6233731.html", "6246213.html", "6247353.html", "6251311.html",
			"6252134.html", "6255530.html", "6256364.html", "6259664.html",
			"6260509.html", "6263434.html", "6264282.html", "6267087.html",
			"6267838.html", "6270464.html", "6290764.html", "6332261.html",
			"6344927.html", "6380393.html", "6384337.html", "6395222.html",
			"6400456.html", "6415451.html", "6417090.html", "6466651.html",
			"6468175.html", "6474507.html", "6478734.html", "6501831.html",
			"6503202.html", "6511037.html", "6512023.html", "6522426.html",
			"6527614.html", "6534639.html", "6535632.html", "6546004.html",
			"6550283.html", "6577946.html", "6582344.html" };

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		File f = new File("F:/Sealion/books/jpnx");
		File[] fs = f.listFiles(/*
								 * new FilenameFilter() {
								 * 
								 * @Override public boolean accept(File dir,
								 * String name) { // TODO Auto-generated method
								 * stub return name.endsWith(".html") &&
								 * !name.startsWith("0index.html"); } }
								 */);
		for (File ff : fs) {
			// File f = new File("F:/Sealion/books/jpnxd/" + i + ".html");
			// f.renameTo(new File("F:/Sealion/books/jpnxd/" + files[i-390]));
			execute2(ff, "F:/Sealion/books/jpnxd2/" + ff.getName());
		}
	}

	static void execute2(File from, String end) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(
				new FileInputStream(from), "GBK"));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(end), "GBK"));
		String line = null;
		while ((line = in.readLine()) != null) {

			out.write(line);
			out.newLine();
		}
		if (from.getName().endsWith(".html")
				&& !from.getName().startsWith("0index.html")) {
			out.write("<br/><br/></body>\n</html>");
		}
		in.close();
		out.close();
	}

	static void execute(String from, String end) throws IOException {
		Pattern p = Pattern.compile("<div id=\"content\">.*</div>");
		BufferedReader in = new BufferedReader(new InputStreamReader(
				new FileInputStream(from), "GBK"));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(end), "GBK"));
		String line = null;
		while ((line = in.readLine()) != null) {
			if (line.contains("<meta name=")) {
				continue;
			} else if (line.contains("page.css")) {
				out.append("<link rel=\"stylesheet\" href=\"page.css\" type=\"text/css\" media=\"all\"  />");
				out.newLine();
				continue;
			} else if (line.contains("article/pagetop.js")) {
				continue;
			} else if (line.contains("/qmshu/top.js")) {
				out.append("    <td colspan=\"3\"></td>");
				out.newLine();
				continue;
			} else if (line.contains("/qmshu/topfeet1.js")) {
				out.append("    <td >");
				out.newLine();
				continue;
			} else if (line
					.contains("<script src=\"/qmshu/topfeet2.js\"></script>")) {
				line = line
						.substring(
								0,
								line.indexOf("<script src=\"/qmshu/topfeet2.js\"></script>"));
				// continue;
			} else if (line.contains("article/pagebottom.js")) {
				continue;
			}
			// if (p.matcher(line).matches()) {
			out.append(line);
			out.newLine();
			// }
		}
		in.close();
		out.close();

	}

}
