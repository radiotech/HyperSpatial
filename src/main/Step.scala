package main

import java.util.TimerTask;
import space._

class Step extends TimerTask {
  def run {
    
    val moveVel = 2*Main.DELTA_STEP*.001;
    val rotVel = .2*Math.PI*2*Main.DELTA_STEP*.001;
    val jumpVel = 3*Main.DELTA_STEP*.001;
    
    var MOVE: Map[Int,Function[Obj.Forces,Obj.Forces]] = Map()
    
    def constructMove(act: Int, t: Int, i: Int, f: Force): (Int,Function[Obj.Forces,Obj.Forces]) = (-1,{fs: Obj.Forces=>{println("w");if(act==1) fs.push(t,i,f) else fs.pull(t,i,f)}})
    
    Main.Keys.foreach(x=>{
      if(x._1 == 'w') MOVE = MOVE+constructMove(x._2,100,1,Force(3,2,-moveVel))
      if(x._1 == 's') MOVE = MOVE+constructMove(x._2,101,1,Force(3,2,moveVel))
      if(x._1 == 'd') MOVE = MOVE+constructMove(x._2,102,1,Force(3,0,-moveVel))
      if(x._1 == 'a') MOVE = MOVE+constructMove(x._2,103,1,Force(3,0,moveVel))
      if(x._1 == ' ') MOVE = MOVE+constructMove(x._2,104,1,Force(3,1,-jumpVel))
      if(x._1 == 'i') MOVE = MOVE+constructMove(x._2,201,1,Force(3,2,1,rotVel))
      if(x._1 == 'k') MOVE = MOVE+constructMove(x._2,202,1,Force(3,1,2,rotVel))
      if(x._1 == 'l') MOVE = MOVE+constructMove(x._2,203,1,Force(3,2,0,rotVel))
      if(x._1 == 'j') MOVE = MOVE+constructMove(x._2,204,1,Force(3,0,2,rotVel))
      if(x._1 == 'o') MOVE = MOVE+constructMove(x._2,205,1,Force(3,1,0,rotVel))
      if(x._1 == 'u') MOVE = MOVE+constructMove(x._2,206,1,Force(3,0,1,rotVel))
      
      /*if(x._1 == 'w') MOVE = MOVE+(-1->((1,Force(Vector(0,0,-moveVel*x._2)))))
      if(x._1 == 's') MOVE = MOVE+(-1->((1,Force(Vector(0,0,moveVel*x._2)))))
      if(x._1 == 'd') MOVE = MOVE+(-1->((1,Force(Vector(-moveVel*x._2,0,0)))))
      if(x._1 == 'a') MOVE = MOVE+(-1->((1,Force(Vector(moveVel*x._2,0,0)))))
      if(x._1 == ' ' && x._2 == 1) MOVE = MOVE+(-1->((1,Force(Vector(0,-jumpVel,0)))))
      if(x._1 == 'i') MOVE = MOVE+(-1->((1,Force(3,2,3,rotVel))))
      if(x._1 == 'k') MOVE = MOVE+(-1->((1,Force(3,3,2,rotVel))))
      if(x._1 == 'l') MOVE = MOVE+(-1->((1,Force(3,1,3,rotVel))))
      if(x._1 == 'j') MOVE = MOVE+(-1->((1,Force(3,3,1,rotVel))))
      if(x._1 == 'o') MOVE = MOVE+(-1->((1,Force(3,1,2,rotVel))))
      if(x._1 == 'u') MOVE = MOVE+(-1->((1,Force(3,2,1,rotVel))))*/
      
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