package space

object Frame {def apply(d: Int) = new Frame(d)}
class Frame(override val D: Int) extends Real {
  
  var contents: List[Real] = Nil
  
  def add(r: Real) {contents = r::contents;println(8);}
  def view(fov: Double): List[Real] = view(p=>{if(p==None) None else {if(p.get.translation.last>(-.0001)) {val scale = fov/(p.get.translation.last+fov+.001); Some(Pt(p.get.translation.map(_*scale)))} else {println(7);None}}})
  
  def cView(f: Function[Option[Pt],Option[Pt]]) = {
    val next = Frame(D-1)
    contents.foreach{_.view(f).foreach(next.add(_))}
    next::Nil
  }
  def draw(g: java.awt.Graphics, f: Function[Pt,(Int,Int,Boolean)]) {println(contents.length);contents.foreach{_.draw(g,f)}}
  
  //def viewC(fov: Double): List[Frame] = view(p=>{val scale = fov/(p.sub.last+fov);if(scale<=0) Some(Pt(p.sub.map(_*scale))) else None})
  //def viewC(f: Function[Pt,Option[Pt]]) = {
    //val next = Frame()
    //List(next)
  //}
  
}

/*Obj(o.points.map(p=>{
      val newP = camera.fs.pos(o.fs.pos(p))
      val scale = fov/(newP.sub.last+fov)
      Pt(newP.sub.map(_*scale))
    })*/