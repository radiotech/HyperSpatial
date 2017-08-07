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
  
  val SPACE = Frame(4)
  val INSPACE = Frame(4)
  val POINTS = List(
      Pt(Vector(-1d,-1,-1,-1)),
      Pt(Vector(1d,-1,-1,-1)),
      Pt(Vector(-1d,1,-1,-1)),
      Pt(Vector(1d,1,-1,-1)),
      Pt(Vector(-1d,-1,1,-1)),
      Pt(Vector(1d,-1,1,-1)),
      Pt(Vector(-1d,1,1,-1)),
      Pt(Vector(1d,1,1,-1)),
      Pt(Vector(-1d,-1,-1,1)),
      Pt(Vector(1d,-1,-1,1)),
      Pt(Vector(-1d,1,-1,1)),
      Pt(Vector(1d,1,-1,1)),
      Pt(Vector(-1d,-1,1,1)),
      Pt(Vector(1d,-1,1,1)),
      Pt(Vector(-1d,1,1,1)),
      Pt(Vector(1d,1,1,1)))
  val LINES = POINTS.combinations(2).filter { ps => Range(0,ps(0).D,1).foldRight(0){(i,acc) => if(ps(0).translation(i)==ps(1).translation(i)) acc else acc+1}==1 }.map(ps=>Ptx2(ps(0),ps(1)))
  INSPACE.translate(3, 7)
  INSPACE.translate(2, 7)
  POINTS.foreach(INSPACE.add(_))
  LINES.foreach(INSPACE.add(_))
  SPACE.add(INSPACE)
  
  //3d object
  //val points = List(Pt(0,-2,4),Pt(-3,-2,0),Pt(4,-2,-1),Pt(0,4,-2));
  
  /*
  val pF = Vector(Pt(0,-.5,0),Pt(45,0,45),Pt(-45,0,45),Pt(45,0,-45),Pt(-45,0,-45))
  val fF = Vector(Face(Vector(pF(0),pF(1),pF(2))),
                  Face(Vector(pF(0),pF(2),pF(3))),
                  Face(Vector(pF(0),pF(3),pF(4))),
                  Face(Vector(pF(0),pF(4),pF(1))),
                  Face(Vector(pF(1),pF(2),pF(3))),
                  Face(Vector(pF(3),pF(4),pF(1))))
  */
  
  
  
  
  /*
  val camP = Vector(Pt(0,0,0))
  val camFace = Vector();
  val camForce = Obj.Forces.zeros(3).add(0,Force(3,0,-2))
  val cam = Obj(camFace,camForce)
  
  val triP = Vector(Pt(-2,-1,-2),Pt(1.5,-1,0),Pt(0,1.5,0),Pt(0,-1,1.5))
  val triFace = triP.combinations(3).map(Face(_)).toVector
  val triForce = Obj.Forces.zeros(3).add(0,Force(3,2,10d)).add(1,Force(3,0,2,Math.PI*2/DELTA_STEP*.5))
  val tri = Obj(triFace,triForce)
  
  val objs = Vector(tri)
  
  var SPACE = Space(1,cam,objs);
  */
  
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
    drawTimer.schedule(drawTask, 1, DELTA_DRAW)
  }
  
}