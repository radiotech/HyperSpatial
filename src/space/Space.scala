package space

object Space {
  def apply(c: Obj, objs: Vector[Obj]) = new Space(c,objs)
}

class Space(val camera: Obj, val objects: Vector[Obj]){
  /*
   * crush(FOV)
   * 
   */
  
  def step(m: Map[Int,(Int,Force)]) = Space(camera.step(m.get(-1)),objects.indices.toVector.map(i=>objects(i).step(m.get(i))))
  
  def view(fov: Double) = FlatSpace(objects.map {o =>
    Obj(o.I,Obj.M(Force.zeros(o.M.D),Force.zeros(o.M.D),Force.zeros(o.M.D),o.I.points.map(p=>{
      val newP = camera.M.pos(o.M.pos(p))
      val scale = fov/(newP.sub.last+fov)
      p->Pt(newP.sub.map(_*scale))
    }).toMap))
  })
  
}