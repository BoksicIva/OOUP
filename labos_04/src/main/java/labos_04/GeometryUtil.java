package labos_04;

import java.awt.geom.Line2D;

public class GeometryUtil {

    /**
     * izračunaj euklidsku udaljenost između dvije točke
     * @param point1
     * @param point2
     * @return
     */
    public static double distanceFromPoint(Point point1, Point point2) {
        return Math.sqrt(Math.pow(point1.getX()-point2.getX(),2)+Math.pow(point1.getY()-point2.getY(),2));
    }

    public static double distanceFromLineSegment(Point s, Point e, Point p) {
        // Izračunaj koliko je točka P udaljena od linijskog segmenta određenog
        // početnom točkom S i završnom točkom E. Uočite: ako je točka P iznad/ispod
        // tog segmenta, ova udaljenost je udaljenost okomice spuštene iz P na S-E.
        // Ako je točka P "prije" točke S ili "iza" točke E, udaljenost odgovara
        //udaljenosti od P do početne/konačne točke segmenta.
        double distance = Math.abs((s.getX()-e.getX())*(s.getY()-p.getY())-(e.getX()-p.getX())*(s.getY()-e.getY()))/distanceFromPoint(s,e); //ja sam racunala udaljenost od ravnine
        if(distance == 0) { //tocka je na ravnini
            distance=Math.min(distanceFromPoint(s,p),distanceFromPoint(e,p));
        }else{ // tocka nije na ravnini
            double lengthOfSegment=distanceFromPoint(s,e);
            double param= ((p.getX()-s.getX())*(e.getX()-s.getX())+(p.getY()-s.getY())*(e.getY()-s.getY()))/lengthOfSegment;
            if(param<=1 && param>0){
                double x=s.getX()+param*(e.getX()-s.getX());
                double y=s.getY()+param*(e.getY()-s.getY());
                distance=Math.pow(distanceFromPoint(new Point((int)x,(int)y),p),2);
            }else{
                distance=Math.min(distanceFromPoint(s,p),distanceFromPoint(e,p));
            }
        }
        return distance;

    }



}
