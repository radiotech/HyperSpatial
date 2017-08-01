package main

import javax.swing.{JFrame,JLabel}
import java.awt.{Dimension,BorderLayout,Color}
import java.awt.event._
import scala.collection.mutable.Queue
import java.util.Timer;
import java.awt.image.{BufferedImage,DataBufferInt}
import javafx.scene.input.KeyCode

import space._;

object Main {
  val PANNEL_WIDTH = 1000
  val PANNEL_HEIGHT = (1000*.5625).toInt
  val VIEW_WIDTH = 500
  val VIEW_HEIGHT = (500*.5625).toInt
  val DELTA_DRAW = 1000/25
  val DELTA_STEP = 1000/25
  
  
  //3d object
  //val points = List(Pt(0,-2,4),Pt(-3,-2,0),Pt(4,-2,-1),Pt(0,4,-2));
  
  val pF = Vector(Pt(0,-.5,0),Pt(45,0,45),Pt(-45,0,45),Pt(45,0,-45),Pt(-45,0,-45))
  val fF = Vector(Face(Vector(pF(0),pF(1),pF(2))),
                  Face(Vector(pF(0),pF(2),pF(3))),
                  Face(Vector(pF(0),pF(3),pF(4))),
                  Face(Vector(pF(0),pF(4),pF(1))),
                  Face(Vector(pF(1),pF(2),pF(3))),
                  Face(Vector(pF(3),pF(4),pF(1))))
  
  val points = Vector(Pt(-2,-1,-2),Pt(1.5,-1,0),Pt(0,1.5,0),Pt(0,-1,1.5))
  
  val faces = points.combinations(3).map(Face(_)).toVector//++Face(Vector(1,3,4),points).shard
  
  //val points2 = points:::Range(3,6,1).toList.map(i=>faces(i).shard(points))
  //val faces2 = faces:::Range(3,6,1).toList.flatMap(i=>faces(i).shard(i-3+5))
  
  val cols = Vector(Color.RED,Color.GREEN,Color.BLUE,Color.MAGENTA)
  
  val objs = Vector(Obj(faces,Obj.Forces.zeros(3).add(0,Force(3,2,10d)).add(1,Force(3,0,2,Math.PI*2/DELTA_STEP*.5)))//,
                    /*Obj(fF   ,Obj.Forces.zeros(3))*/)
  
  val cam = Obj(Vector(),Obj.Forces.zeros(3).add(0,Force(3,1,-2)))
  
  var SPACE = Space(cam,objs);
  var Keys: Map[Char,Int] = Map()
  
  var grid = new DataPanel(PANNEL_WIDTH, PANNEL_HEIGHT);
  var window = new JFrame("NEW FlatLand Thing.. :p :p")
  window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  window.getContentPane().add(grid, BorderLayout.WEST);
  window.pack();
  window.setVisible(true);
  
  def main(args: Array[String]): Unit = {
    
    
    val stepTimer = new Timer()
    val drawTimer = new Timer()
    
    val stepTask = new Step()
    val drawTask = new Draw()
    
    stepTimer.schedule(stepTask, 0, DELTA_STEP)
    drawTimer.schedule(drawTask, DELTA_DRAW, DELTA_DRAW)
  }
  
}