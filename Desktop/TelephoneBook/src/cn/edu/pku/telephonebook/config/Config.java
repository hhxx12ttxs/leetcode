package cn.edu.pku.telephonebook.config;

import java.io.File;

import android.os.Environment;

public class Config {	   
   //头像有关的常量
   public static final int CAMERA_PHOTO = 1;
   public static final int PICK_PHOTO = 2;
   public static final int SET_PHOTO = 3;
   public static final int PHOTO_ASPECTX = 1;
   public static final int PHOTO_ASPECTY = 1;
   public static final int PHOTO_OUTPUTX = 88;
   public static final int PHOTO_OUTPUTY = 88;
   public static final File PHOTO_DIR = new File(Environment.getExternalStorageDirectory() + "/DCIM/Camera");
   
   public static final int NO_NEED_SET_BELOW = -1;
   //从0开始不断增加
   public static int DYNAMIC_VIEW_ID = 0;
}
