import java.util.*;

public class SmartCare {

    static class Patient {
        int id, age;
        String name, gender, phone, condition;

        Patient(int id, String name, int age, String gender,
                String phone, String condition) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.gender = gender;
            this.phone = phone;
            this.condition = condition;
        }
    }

    static class Doctor {
        int id;
        String name, specialization, phone;

        Doctor(int id, String name, String specialization, String phone) {
            this.id = id;
            this.name = name;
            this.specialization = specialization;
            this.phone = phone;
        }
    }

    static class Appointment {
        int id, patientId, doctorId;
        String date, time;

        Appointment(int id, int p, int d, String date, String time) {
            this.id = id;
            patientId = p;
            doctorId = d;
            this.date = date;
            this.time = time;
        }
    }

    static class Emergency {
        String patient, priority;
        int rank;

        Emergency(String patient, String priority, int rank) {
            this.patient = patient;
            this.priority = priority;
            this.rank = rank;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        HashMap<Integer, Patient> patients = new HashMap<>();
        LinkedList<Appointment> appointments = new LinkedList<>();

        // 1. Patient Management
        System.out.println("===== PATIENT MANAGEMENT =====");

        System.out.print("Enter Patient ID: ");
        int pid = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Gender: ");
        String gender = sc.nextLine();

        System.out.print("Enter Phone: ");
        String phone = sc.nextLine();

        System.out.print("Enter Condition: ");
        String condition = sc.nextLine();

        Patient p = new Patient(pid, name, age, gender, phone, condition);
        patients.put(pid, p);

        System.out.println("\nPatient Added Successfully!");

        // 2. Doctor Management
        System.out.println("\n===== DOCTOR MANAGEMENT =====");

        System.out.print("Doctor ID: ");
        int did = sc.nextInt();
        sc.nextLine();

        System.out.print("Name: ");
        String dname = sc.nextLine();

        System.out.print("Specialization: ");
        String specialization = sc.nextLine();

        System.out.print("Phone: ");
        String dphone = sc.nextLine();

        Doctor d = new Doctor(did, dname, specialization, dphone);

        System.out.println("\nDoctor Added Successfully!");

        // 3. Appointment Management
        System.out.println("\n===== APPOINTMENT MANAGEMENT =====");

        System.out.print("Appointment ID: ");
        int aid = sc.nextInt();

        System.out.print("Patient ID: ");
        int apid = sc.nextInt();

        System.out.print("Doctor ID: ");
        int adid = sc.nextInt();
        sc.nextLine();

        System.out.print("Date: ");
        String date = sc.nextLine();

        System.out.print("Time: ");
        String time = sc.nextLine();

        if (patients.containsKey(apid) && apid == pid && adid == did) {
            appointments.add(new Appointment(aid, apid, adid, date, time));
            System.out.println("\nAppointment Scheduled Successfully!");
            System.out.println("Patient: " + p.name);
            System.out.println("Doctor: " + d.name);
            System.out.println("Date: " + date);
            System.out.println("Time: " + time);
            System.out.println("Status: Scheduled");
        } else {
            System.out.println("Patient or Doctor not found!");
        }

        // 4. Emergency Queue
        System.out.println("\n===== EMERGENCY MANAGEMENT =====");

        PriorityQueue<Emergency> queue =
                new PriorityQueue<>(Comparator.comparingInt(e -> e.rank));

        System.out.print("Enter number of emergency cases: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {

            System.out.print("Enter Patient Name: ");
            String ep = sc.nextLine();

            System.out.print("Enter Priority (Immediate/Very Urgent/Urgent/Standard): ");
            String priority = sc.nextLine();

            int rank;

            switch (priority.toLowerCase()) {
                case "immediate":
                    rank = 1;
                    break;
                case "very urgent":
                    rank = 2;
                    break;
                case "urgent":
                    rank = 3;
                    break;
                case "standard":
                    rank = 4;
                    break;
                default:
                    System.out.println("Invalid priority! Case skipped.");
                    continue;
            }

            queue.add(new Emergency(ep, priority, rank));
        }

        System.out.println("\nEmergency Queue:");
        int count = 1;

        while (!queue.isEmpty()) {
            Emergency e = queue.poll();
            System.out.println(count++ + ". " + e.patient + " - " + e.priority);
        }

        // 5. Patient Search
        System.out.println("\n===== PATIENT SEARCH =====");

        System.out.print("Enter Patient ID to Search: ");
        int searchId = sc.nextInt();

        Patient found = patients.get(searchId);

        if (found != null) {
            System.out.println("\nPatient Found!");
            System.out.println("ID: " + found.id);
            System.out.println("Name: " + found.name);
            System.out.println("Age: " + found.age);
            System.out.println("Condition: " + found.condition);
        } else {
            System.out.println("Patient Not Found!");
        }

        // 6. Hospital Analytics
        System.out.println("\n======= SMARTCARE ANALYTICS =======");
        System.out.println("Total Patients       : " + patients.size());
        System.out.println("Total Doctors        : 1");
        System.out.println("Total Appointments   : " + appointments.size());
        System.out.println("Emergency Cases      : " + n);
        System.out.println("===================================");
        System.out.println("Hospital Report Generated Successfully!");

        sc.close();
    }
}