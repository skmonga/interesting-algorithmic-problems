package datastructures;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * This code is related to understanding the usage of PriorityQueue in Java.
 * To understand priority queue, refer its doc and read about how to use it at
 * http://stackoverflow.com/questions/683041/java-how-do-i-use-a-priorityqueue
 * The example here used is one of answers from the above stack question only.
 */
public class PriorityQueueTest {

	static class Patient {

		private int id;

		private String name;

		private boolean emergencyCase;

		public Patient(int id, String name, boolean emergencyCase) {
			this.id = id;
			this.name = name;
			this.emergencyCase = emergencyCase;
		}

		/**
		 * @return the id
		 */
		public int getId() {
			return id;
		}

		/**
		 * @param id
		 *            the id to set
		 */
		public void setId(int id) {
			this.id = id;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the emergencyCase
		 */
		public boolean isEmergencyCase() {
			return emergencyCase;
		}

		/**
		 * @param emergencyCase
		 *            the emergencyCase to set
		 */
		public void setEmergencyCase(boolean emergencyCase) {
			this.emergencyCase = emergencyCase;
		}
	}

	public static void main(String[] args) {
		PriorityQueue<Patient> patientQueue = new PriorityQueue<Patient>(10, new Comparator<Patient>() {
			public int compare(Patient patient1, Patient patient2) {
				return (patient1.isEmergencyCase() == patient2.isEmergencyCase())
						? (Integer.valueOf(patient1.getId()).compareTo(patient2.getId()))
						: (patient1.isEmergencyCase() ? -1 : 1);
			}
		});

		patientQueue.add(new Patient(1, "Patient1", false));
		patientQueue.add(new Patient(2, "Patient2", false));
		patientQueue.add(new Patient(3, "Patient3", true));
		patientQueue.add(new Patient(4, "Patient4", false));
		patientQueue.add(new Patient(5, "Patient5", true));

		System.out.println();
		System.out.print("Doctor's waiting for patients  : ");
		while (true) {
			Patient currentPatient = patientQueue.poll();
			if (currentPatient == null) {
				break;
			}

			System.out.print(currentPatient.getName() + " <-- ");
		}
		System.out.println();
	}

}
