package chap11.modele;

public class ImplemService2 implements Service2 {

	public ImplemService2(){
		System.out.println("Initialisation du service 2 de type " + this.getClass());
	}
	
	
	@Override
	public int f() {
		return 17;
	}

}
