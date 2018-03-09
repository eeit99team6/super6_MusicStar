package _global.utils;

public class Constant
{
	public static final String securityKey = "MusicStarMainKey";  //16，24，32
	public static final int fileUploadMemoryThreshold = 1024 * 1024 * 5; // 5MB
	public static final int fileUploadMaxFileSize = 1024 * 1024 * 25; // 25MB
	public static final int fileUploadMaxRequestSize = 1024 * 1024 * 50; // 50MB
	public static final String imagesDirectory = "/FileSource/images/"; //存放影像檔的目錄
	public static final String profilesDirectory = "/FileSource/images/profiles/"; //存放會員頭像的目錄
	public static final String coverDirectory = "/FileSource/images/cover/"; //存放音樂封面的目錄
	public static final String audioDirectory = "/FileSource/audios/"; //存放音樂封面的目錄  audioDirectory + loginOK.getMemberId +/ yyMMDDhhmmSSS +"_" + 

}
