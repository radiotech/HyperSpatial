package main

import java.util.TimerTask;

import javax.swing.{JFrame,JLabel}
import java.awt.{Dimension,BorderLayout,Color}
import java.awt.event._
import scala.collection.mutable.Queue
import java.util.Timer;
import java.awt.image.{BufferedImage,DataBufferInt}
import java.awt.Graphics
import javafx.scene.input.KeyCode

import space._

class Draw extends TimerTask {
  def run {
    
    val img = new BufferedImage(Main.VIEW_WIDTH,Main.VIEW_HEIGHT,BufferedImage.TYPE_INT_RGB)
    val g = img.getGraphics
    g.setColor(Color.WHITE)
    g.fillRect(0, 0, Main.VIEW_WIDTH, Main.VIEW_HEIGHT)
    
    g.setColor(Color.BLACK)
     
    val FLAT_SPACE = Main.SPACE.view(1);
    
    lineDrawFlatSpace(g, FLAT_SPACE,(-1,-.5625,2,1.125));
    
    Main.grid.draw(img)
  }
  
  def lineDrawFlatSpace(g: Graphics, w: FlatSpace, view: (Double,Double,Double,Double)) {
    w.objs.foreach { o =>
      o.I.faces.foreach {f => {
        f(o.M.ptMap).combinations(2).foreach { l =>
          g.drawLine(
            ((l(0).x-view._1)/view._3*Main.VIEW_WIDTH).toInt,
            ((l(0).y*(-1)-view._2)/view._4*Main.VIEW_HEIGHT).toInt,
            ((l(1).x-view._1)/view._3*Main.VIEW_WIDTH).toInt,
            ((l(1).y*(-1)-view._2)/view._4*Main.VIEW_HEIGHT).toInt)
          }
        }
      }
    }
  }
}