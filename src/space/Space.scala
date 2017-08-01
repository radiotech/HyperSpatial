package space

object Space {
  def apply(c: Obj, objs: Vector[Obj]) = new Space(c,objs)
}

class Space(val camera: Obj, val objects: Vector[Obj]){
  /*
   * crush(FOV)
   * 
   */
  
  def step(m: Map[Int,Function[Obj.Forces,Obj.Forces]]) = Space(camera.step(m.getOrElse(-1, f=>f)),objects.indices.toVector.map(i=>objects(i).step(m.getOrElse(i,f=>f))))
  
  def view(fov: Double, split: Function[Face,Boolean]) = FlatSpace(objects.map{_.view(fov,split,camera.fs.pos)})
  
}

/*Obj(o.points.map(p=>{
      val newP = camera.fs.pos(o.fs.pos(p))
      val scale = fov/(newP.sub.last+fov)
      Pt(newP.sub.map(_*scale))
    })*/