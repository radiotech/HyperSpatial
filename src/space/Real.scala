package space

import java.awt.Graphics

trait Real {
  
  val D: Int
  var translation = Vector.tabulate(D){i=>0d}
  var rotation = Vector.tabulate(D){i=>Vector.tabulate(D){j=>if(i==j) 1d else 0}}
  
  def translate(p: Option[Pt]) = if(p==None) None else Some(Pt(Range(0,D,1).toVector.map{i=>p.get(i)+translation(i)}))
  def rotate(p: Option[Pt]) = if(p==None) None else Some(Pt(Range(0,D,1).toVector.map{i=>Range(0,D,1).toVector.foldRight(0d){(j,acc)=>acc+p.get(j)*rotation(i)(j)}}))
  
  def rotate(a1: Int, a2: Int, by: Double) = {rotation = rotation.map{a=>Range(0,D,1).toVector.map{i=>if(i==a1) Math.cos(by)*a(a1)-Math.sin(by)*a(a2) else if(i==a2) Math.sin(by)*a(a1)+Math.cos(by)*a(a2) else a(i)}}}
  def translate(by: Vector[Double]) {translation = Range(0,D,1).toVector.map(i=>translation(i)+by(i))}
  def translate(axis: Int, dis: Double) {translation = Range(0,D,1).toVector.map(i=>if(i==axis) translation(i)+dis else translation(i))}
  
  def view(f: Function[Option[Pt],Option[Pt]]): List[Real] = cView(p=>f(translate(rotate(p))))
  
  def cView(f: Function[Option[Pt],Option[Pt]]): List[Real]
  def draw(g: Graphics, f: Function[Pt,(Int,Int,Boolean)])
  //def step()
}