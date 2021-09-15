package labos_04.renderer;

import labos_04.Point;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SVGRendererImpl implements Renderer{

    private final List<String> lines = new ArrayList<>();
    private String fileName;

    public SVGRendererImpl(String fileName) {
        // zapamti fileName; u lines dodaj zaglavlje SVG dokumenta:
        // <svg xmlns=... >
        // ...
        this.fileName=fileName;
        this.lines.add("<svg  xmlns=\"http://www.w3.org/2000/svg\"\n" +
                "      xmlns:xlink=\"http://www.w3.org/1999/xlink\">");
    }

    public void close() throws IOException {
        // u lines još dodaj završni tag SVG dokumenta: </svg>
        // sve retke u listi lines zapiši na disk u datoteku
        // ...
        lines.add("</svg>");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
           lines.forEach(l->{
               try {
                   bw.write(l);
                   bw.newLine();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           });
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void drawLine(Point s, Point e) {
        // Dodaj u lines redak koji definira linijski segment:
        // <line ... />
        String line="    <line x1=\""+s.getX()+"\" y1=\""+s.getY()+"\" x2=\""+e.getX()+"\" y2=\""+e.getY()+"\" style=\"stroke:#0000FF;\"/>";
        lines.add(line);
    }

    @Override
    public void fillPolygon(Point[] points) {
        // Dodaj u lines redak koji definira popunjeni poligon:
        // <polygon points="..." style="stroke: ...; fill: ...;" />
        String line="    <polygon points=\"";
        for(Point point:points){
            line+=point.getX()+","+point.getY()+" ";
        }
        line+="\" style=\"stroke:#FF0000; fill:#0000FF;\"/>";
        lines.add(line);
    }
}
