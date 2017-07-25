package main

import java.util.TimerTask;
import space._

class Step extends TimerTask {
  def run {
    
    val moveVel = Main.DELTA_STEP*.003;
    val rotVel = Main.DELTA_STEP*Math.PI*2*.002;
    val jumpVel = Main.DELTA_STEP*Math.PI*.002;
    
    var MOVE: Map[Int,(Int,Force)] = Map()
    
    Main.Keys.foreach(x=>{
      if(x._1 == 'w') MOVE = MOVE+(-1->((1,Force(Vector(0,0,-moveVel*x._2)))))
      if(x._1 == 's') MOVE = MOVE+(-1->((1,Force(Vector(0,0,moveVel*x._2)))))
      if(x._1 == 'd') MOVE = MOVE+(-1->((1,Force(Vector(-moveVel*x._2,0,0)))))
      if(x._1 == 'a') MOVE = MOVE+(-1->((1,Force(Vector(moveVel*x._2,0,0)))))
      if(x._1 == ' ' && x._2 == 1) MOVE = MOVE+(-1->((1,Force(Vector(0,-jumpVel,0)))))
      if(x._1 == 'i') MOVE = MOVE+(-1->((1,Force(3,2,3,rotVel))))
      if(x._1 == 'k') MOVE = MOVE+(-1->((1,Force(3,3,2,rotVel))))
      if(x._1 == 'l') MOVE = MOVE+(-1->((1,Force(3,1,3,rotVel))))
      if(x._1 == 'j') MOVE = MOVE+(-1->((1,Force(3,3,1,rotVel))))
      if(x._1 == 'o') MOVE = MOVE+(-1->((1,Force(3,1,2,rotVel))))
      if(x._1 == 'u') MOVE = MOVE+(-1->((1,Force(3,2,1,rotVel))))
      
    })
    
    Main.Keys = Map()
    
    Main.SPACE = Main.SPACE.step(MOVE)
    /*
    val axes = Rotation(3)(0,2,Math.PI/130*steps).axes;
    val axes1 = Rotation(3)(0,1,Math.PI/70*steps*1).axes;
    val axes2 = Rotation(3)(1,2,Math.PI/50*steps*1).axes;
    val points = Main.points.map { p => 
      Pt(axes.map { a => 
        p.dot(a)
      })
    }
    val points2 = points.map { p => 
      Pt(axes1.map { a => 
        p.dot(a)
      })
    }
    val points3 = points2.map { p => 
      Pt(axes2.map { a => 
        p.dot(a)
      })
    }
    //val points = Pt(0,.1,0)::
    val points4 = points3.map { p => 
      Pt(p.x,p.y,p.z+6)
    }
    val objs = Main.objs//Vector(Obj(Main.objs(0).I,Obj.M(points4,Main.faces.flatMap{f=>Face(f.I,Face.M(points4)).shard})))
    
    
    def view(fov: Double) = FlatSpace(objs.map {o => 
      val projectedPoints = o.M.points.map{p=>
        val scale = fov/(p.sub.last/*+pos.sub.last*/+fov)
        Pt(p.sub.indices.init.toVector.map(a=>(p(a)/*+pos(a)*/)*scale))
      }
      val projectedFaces = o.M.faces.map{f=>
        Face(f.I,Face.M(projectedPoints))
      }
      Obj(o.I,Obj.M(projectedPoints,projectedFaces))
    })
    */
    
    //Main.SPACE = Space(Obj(Vector(),Vector(),Vector(),),objs)
    
  }
}