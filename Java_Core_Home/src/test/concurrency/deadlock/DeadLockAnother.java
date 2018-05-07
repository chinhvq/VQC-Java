package test.concurrency.deadlock;

public class DeadLockAnother {

	public static void main(String[] args) {
		PolitePerson person1 = new PolitePerson("PERSON1");
		PolitePerson person2 = new PolitePerson("PERSON2");

		new Thread(new Runnable() {

			@Override
			public void run() {
				person1.sayHello(person2);
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				person2.sayHello(person1);
			}
		}).start();

	}

	private static class PolitePerson {
		private final String name;

		protected PolitePerson(String name) {
			this.name = name;
		}

		public synchronized void sayHello(PolitePerson person) {
			System.out.format("%s : %s " + "has said hello to me! %n ", this.name, person.name);
			person.sayHelloBack(this);
		}

		public synchronized void sayHelloBack(PolitePerson person) {
			System.out.format("%s : %s " + "has said hello back to me! %n ", this.name, person.name);
		}
	}

}
