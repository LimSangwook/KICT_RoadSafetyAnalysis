package Utility;

import java.util.Iterator;

import RawData.RawDataModel;
import RawData.RawDataModel.TrackData;
import Utility.Util.UTM;

public class Util {

	public static int GetBaseTargetTrackIdx(RawDataModel rawData) {
		int BASE_LANE = 2;
		Iterator<TrackData> iter = rawData.GetTrackDatas().iterator();
		TrackData minTrackData = null; 
		int minTrackIdx = -1;
		for(int idx = 0 ; idx < rawData.GetTrackDatas().size() ; idx ++) {
			TrackData nowTrackData = rawData.GetTrackDatas().get(idx);
			if (GetLaneNumber(nowTrackData.X) != BASE_LANE) {
				continue;
			}
			if (nowTrackData.isValid() == false) {
				continue;
			}
			if (minTrackData == null) {
				minTrackData = nowTrackData;
				minTrackIdx = idx;
				continue;
			}
			if (minTrackData.Y > nowTrackData.Y) {
				minTrackData = nowTrackData;
				minTrackIdx = idx;
			}
		}
		return minTrackIdx;
	}
	public static class UTM {
		public double X;
		public double Y;
		int Zone;
	}

	public static int GetLaneNumber(double valX) {
		int output = -1;
        if (valX < -5.25)
            output = -1;
        else if (valX <= -1.75)
            output = 1;
        else if (valX < 1.75) 
            output = 2;
        else if (valX < 5.25) 
            output = 3;
        else if (valX < 8.75) 
            output = 4;
        else if (valX < 12.25) 
            output = 5;
        else if (valX < 15.75) 
            output = 6;
		return output;
	}
	public static String GetSectionName(double latitude, double longitude) {
		return ICDataManager.getInstance().GetSectionName(latitude, longitude);
	}
	
	public static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
		return DistanceCalculator.distance(lat1, lon1, lat2, lon2, unit);
 	}
	public static class Deg2UTM
	{
	    public double Easting;
	    public double Northing;
	    int Zone;
	    char Letter;
	    public Deg2UTM(double Lat,double Lon)
	    {
	        Zone= (int) Math.floor(Lon/6+31);
	        if (Lat<-72) 
	            Letter='C';
	        else if (Lat<-64) 
	            Letter='D';
	        else if (Lat<-56)
	            Letter='E';
	        else if (Lat<-48)
	            Letter='F';
	        else if (Lat<-40)
	            Letter='G';
	        else if (Lat<-32)
	            Letter='H';
	        else if (Lat<-24)
	            Letter='J';
	        else if (Lat<-16)
	            Letter='K';
	        else if (Lat<-8) 
	            Letter='L';
	        else if (Lat<0)
	            Letter='M';
	        else if (Lat<8)  
	            Letter='N';
	        else if (Lat<16) 
	            Letter='P';
	        else if (Lat<24) 
	            Letter='Q';
	        else if (Lat<32) 
	            Letter='R';
	        else if (Lat<40) 
	            Letter='S';
	        else if (Lat<48) 
	            Letter='T';
	        else if (Lat<56) 
	            Letter='U';
	        else if (Lat<64) 
	            Letter='V';
	        else if (Lat<72) 
	            Letter='W';
	        else
	            Letter='X';
	        Easting=0.5*Math.log((1+Math.cos(Lat*Math.PI/180)*Math.sin(Lon*Math.PI/180-(6*Zone-183)*Math.PI/180))/(1-Math.cos(Lat*Math.PI/180)*Math.sin(Lon*Math.PI/180-(6*Zone-183)*Math.PI/180)))*0.9996*6399593.62/Math.pow((1+Math.pow(0.0820944379, 2)*Math.pow(Math.cos(Lat*Math.PI/180), 2)), 0.5)*(1+ Math.pow(0.0820944379,2)/2*Math.pow((0.5*Math.log((1+Math.cos(Lat*Math.PI/180)*Math.sin(Lon*Math.PI/180-(6*Zone-183)*Math.PI/180))/(1-Math.cos(Lat*Math.PI/180)*Math.sin(Lon*Math.PI/180-(6*Zone-183)*Math.PI/180)))),2)*Math.pow(Math.cos(Lat*Math.PI/180),2)/3)+500000;
	        Easting=Math.round(Easting*100)*0.01;
	        Northing = (Math.atan(Math.tan(Lat*Math.PI/180)/Math.cos((Lon*Math.PI/180-(6*Zone -183)*Math.PI/180)))-Lat*Math.PI/180)*0.9996*6399593.625/Math.sqrt(1+0.006739496742*Math.pow(Math.cos(Lat*Math.PI/180),2))*(1+0.006739496742/2*Math.pow(0.5*Math.log((1+Math.cos(Lat*Math.PI/180)*Math.sin((Lon*Math.PI/180-(6*Zone -183)*Math.PI/180)))/(1-Math.cos(Lat*Math.PI/180)*Math.sin((Lon*Math.PI/180-(6*Zone -183)*Math.PI/180)))),2)*Math.pow(Math.cos(Lat*Math.PI/180),2))+0.9996*6399593.625*(Lat*Math.PI/180-0.005054622556*(Lat*Math.PI/180+Math.sin(2*Lat*Math.PI/180)/2)+4.258201531e-05*(3*(Lat*Math.PI/180+Math.sin(2*Lat*Math.PI/180)/2)+Math.sin(2*Lat*Math.PI/180)*Math.pow(Math.cos(Lat*Math.PI/180),2))/4-1.674057895e-07*(5*(3*(Lat*Math.PI/180+Math.sin(2*Lat*Math.PI/180)/2)+Math.sin(2*Lat*Math.PI/180)*Math.pow(Math.cos(Lat*Math.PI/180),2))/4+Math.sin(2*Lat*Math.PI/180)*Math.pow(Math.cos(Lat*Math.PI/180),2)*Math.pow(Math.cos(Lat*Math.PI/180),2))/3);
	        if (Letter<'M')
	            Northing = Northing + 10000000;
	        Northing=Math.round(Northing*100)*0.01;
	    }
	}
}