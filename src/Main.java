import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Integer> alter = new ArrayList<>();
        ArrayList<String> geschlecht = new ArrayList<>();
        ArrayList<Double> bmi = new ArrayList<>();
        ArrayList<Integer> children = new ArrayList<>();
        ArrayList<Boolean> smoker = new ArrayList<>();
        ArrayList<String> region = new ArrayList<>();
        ArrayList<Double> charges = new ArrayList<>();


        csvLesen("/Users/ingrid.koutcherova/Downloads/uebun3_aufgabe3_2.11.25/src/insurance.csv", alter, geschlecht, bmi, children, smoker, region, charges);

        bubbleSort(alter, geschlecht, bmi, children, smoker, region, charges);

        for (int i = 0; i < geschlecht.size(); i++) {
            System.out.println(geschlecht.get(i) + " " + alter.get(i) + " " + bmi.get(i) + " " + children.get(i) + " " + smoker.get(i) + " " + region.get(i) + " " + charges.get(i));
        }

        Scanner sc = new Scanner(System.in);

        boolean b = true;

        while (b) {
            System.out.println("Wählen Sie eine Operation: ");
            System.out.println("1 Alle Kunden anzeigen");
            System.out.println("12 Nur weibliche Kunden anzeigen");
            System.out.println("13 Nur männliche Kunden anzeigen");
            System.out.println("14 Nur rauchenden Kunden anzeigen");
            System.out.println("15 Nur nicht rauchende Kunden anzeigen");
            System.out.println("21 Statistik vom Alter ausgeben");
            System.out.println("22 Statistik vom BMI ausgeben");
            System.out.println("23 Statistik vom Beträgen ausgeben");
            System.out.println("3 ID suchen");
            System.out.println("4 Kunden löschen");
            System.out.println("5 Kunden einfügen");

            int wahl = sc.nextInt();

            if (wahl == 1) {
                alleAnzeigen(alter,  geschlecht, bmi, children, smoker, region, charges);
            }
            if (wahl == 12) {
                wAnzeigen(alter, geschlecht, bmi, children, smoker, region, charges);
            }
            if (wahl == 13) {
                mAnzeigen(alter, geschlecht, bmi, children, smoker, region, charges);
            }
            if (wahl == 14) {
                rAnzeigen(alter, geschlecht, bmi, children, smoker, region, charges);
            }
            if (wahl == 15) {
                nichtrAnzeigen(alter, geschlecht, bmi, children, smoker, region, charges);
            }
            if (wahl == 21) {
                float d = durchschnittAlter(alter);
                double s = standardabweichungAlter(alter, d);
                statistikAusgebenAlter(d, s, maxAlter(alter), minAlter(alter));
            }
            if (wahl == 22) {
                float d = durchschnittBmi(bmi);
                double s = standardabweichungBmi(bmi, d);
                statistikAusgebenBmi(d, s, maxBmi(bmi), minBmi(bmi));
            }
            if (wahl == 23) {
                float d = durchschnittCharge(charges);
                double s = standardabweichungCharge(charges, d);
                statistikAusgebenCharge(d, s, maxCharge(charges), minCharge(charges));
            }
            if (wahl == 3) {
                System.out.println("Geben Sie Kunden-ID ein: ");
                int id = sc.nextInt();
                IDSuchen(id, alter, geschlecht, bmi, children, smoker, region, charges);
            }
            if (wahl == 4) {
                int id = sc.nextInt();
                kundenLöschen(id, alter, geschlecht, bmi, children, smoker, region, charges);
            }
            if (wahl == 5) {
                kundenEinfügen(alter, geschlecht, bmi, children, smoker, region, charges);
            } else if (wahl == 0) {
                b = false;
            }
        }

    }

    public static void csvLesen(String filename, ArrayList<Integer> alter, ArrayList<String> geschlecht, ArrayList<Double> bmi, ArrayList<Integer> children, ArrayList<Boolean> smoker, ArrayList<String> region, ArrayList<Double> charges) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));
        if (sc.hasNextLine()) sc.nextLine();
        while (sc.hasNextLine()) {
            String[] werte = sc.nextLine().split(",");

            alter.add(Integer.valueOf(werte[0].trim()));
            geschlecht.add(werte[1].trim());
            bmi.add(Double.valueOf(werte[2].trim()));
            children.add(Integer.valueOf(werte[3].trim()));
            String smokerStr = werte[4].trim();
            boolean smokerBool = smokerStr.equals("yes");
            smoker.add(smokerBool);
            region.add(werte[5].trim());
            charges.add(Double.valueOf(werte[6].trim()));
        }
        sc.close();
    }

    public static void alleAnzeigen(ArrayList<Integer> alter, ArrayList<String> geschlecht, ArrayList<Double> bmi,  ArrayList<Integer> children, ArrayList<Boolean> smoker, ArrayList<String> region, ArrayList<Double> charges) {
        for (int id = 0; id < alter.size(); id++){

            System.out.println("Kunde " + id);
            System.out.println("Alter " + alter.get(id));
            System.out.println("Geschlecht " + geschlecht.get(id));
            System.out.println("BMI " + bmi.get(id));
            System.out.println("Kinder " + children.get(id));
            System.out.println("Raucher " + smoker.get(id));
            System.out.println("Region " + region.get(id));
            System.out.println("Betrag " + charges.get(id));
        }
    }

    public static void wAnzeigen(ArrayList<Integer> alter, ArrayList<String> geschlecht, ArrayList<Double> bmi,  ArrayList<Integer> children, ArrayList<Boolean> smoker, ArrayList<String> region, ArrayList<Double> charges) {
        for (int id = 0; id < alter.size(); id++) {
            if (geschlecht.get(id).equals("female")) {

                System.out.println("Kunde " + id);
                System.out.println("Alter " + alter.get(id));
                System.out.println("Geschlecht " + geschlecht.get(id));
                System.out.println("BMI " + bmi.get(id));
                System.out.println("Kinder " + children.get(id));
                System.out.println("Raucher " + smoker.get(id));
                System.out.println("Region " + region.get(id));
                System.out.println("Betrag " + charges.get(id));
            }
        }
    }

    public static void mAnzeigen(ArrayList<Integer> alter, ArrayList<String> geschlecht, ArrayList<Double> bmi,  ArrayList<Integer> children, ArrayList<Boolean> smoker, ArrayList<String> region, ArrayList<Double> charges) {
        for (int id = 0; id < alter.size(); id++) {
            if (geschlecht.get(id).equals("male")) {

                System.out.println("Kunde " + id);
                System.out.println("Alter " + alter.get(id));
                System.out.println("Geschlecht " + geschlecht.get(id));
                System.out.println("BMI " + bmi.get(id));
                System.out.println("Kinder " + children.get(id));
                System.out.println("Raucher " + smoker.get(id));
                System.out.println("Region " + region.get(id));
                System.out.println("Betrag " + charges.get(id));
            }
        }
    }

    public static void rAnzeigen(ArrayList<Integer> alter, ArrayList<String> geschlecht, ArrayList<Double> bmi,  ArrayList<Integer> children, ArrayList<Boolean> smoker, ArrayList<String> region, ArrayList<Double> charges) {
        for (int id = 0; id < alter.size(); id++) {
            if (smoker.get(id).equals(true)) {

                System.out.println("Kunde " + id);
                System.out.println("Alter " + alter.get(id));
                System.out.println("Geschlecht " + geschlecht.get(id));
                System.out.println("BMI " + bmi.get(id));
                System.out.println("Kinder " + children.get(id));
                System.out.println("Raucher " + smoker.get(id));
                System.out.println("Region " + region.get(id));
                System.out.println("Betrag " + charges.get(id));
            }
        }
    }

    public static void nichtrAnzeigen(ArrayList<Integer> alter, ArrayList<String> geschlecht, ArrayList<Double> bmi,  ArrayList<Integer> children, ArrayList<Boolean> smoker, ArrayList<String> region, ArrayList<Double> charges) {
        for (int id = 0; id < alter.size(); id++) {
            if (smoker.get(id).equals(false)) {

                System.out.println("Kunde " + id);
                System.out.println("Alter " + alter.get(id));
                System.out.println("Geschlecht " + geschlecht.get(id));
                System.out.println("BMI " + bmi.get(id));
                System.out.println("Kinder " + children.get(id));
                System.out.println("Raucher " + smoker.get(id));
                System.out.println("Region " + region.get(id));
                System.out.println("Betrag " + charges.get(id));
            }
        }
    }

    public static float durchschnittAlter(ArrayList<Integer> alter) {
        float summe = 0;
        for (int i = 0; i < alter.size(); i++) {
            summe += alter.get(i);
        }
        return summe / alter.size();
    }

    public static float durchschnittBmi(ArrayList<Double> bmi) {
        float summe = 0;
        for (int i = 0; i < bmi.size(); i++) {
            summe += bmi.get(i);
        }
        return summe / bmi.size();
    }

    public static float durchschnittCharge(ArrayList<Double> charges) {
        float summe = 0;
        for (int i = 0; i < charges.size(); i++) {
            summe += charges.get(i);
        }
        return summe / charges.size();
    }

    public static double standardabweichungAlter(ArrayList<Integer> alter, float durchschnitt) {
        float quadratSumme = 0;
        for (int i = 0; i < alter.size(); i++) {
            quadratSumme += Math.pow(alter.get(i) - durchschnitt, 2);
        }
        return Math.sqrt(quadratSumme / alter.size());
    }

    public static double standardabweichungBmi(ArrayList<Double> bmi, float durchschnitt) {
        float quadratSumme = 0;
        for (int i = 0; i < bmi.size(); i++) {
            quadratSumme += Math.pow(bmi.get(i) - durchschnitt, 2);
        }
        return Math.sqrt(quadratSumme / bmi.size());
    }

    public static double standardabweichungCharge(ArrayList<Double> charges, float durchschnitt) {
        float quadratSumme = 0;
        for (int i = 0; i < charges.size(); i++) {
            quadratSumme += Math.pow(charges.get(i) - charges.get(i), 2);
        }
        return Math.sqrt(quadratSumme / charges.size());
    }

    public static int maxAlter(ArrayList<Integer> alter) {
        int max = alter.get(0);
        for (int i = 0; i < alter.size(); i++) {
            if (alter.get(i) > max) {
                max = alter.get(i);
            }
        }
        return max;
    }

    public static double maxBmi(ArrayList<Double> bmi) {
        Double max = bmi.get(0);
        for (int i = 0; i < bmi.size(); i++) {
            if (bmi.get(i) > max) {
                max = bmi.get(i);
            }
        }
        return max;
    }

    public static double maxCharge(ArrayList<Double> charges) {
        Double max = charges.get(0);
        for (int i = 0; i < charges.size(); i++) {
            if (charges.get(i) > max) {
                max = charges.get(i);
            }
        }
        return max;
    }

    public static int minAlter(ArrayList<Integer> alter) {
        int min = alter.get(0);
        for (int i = 0; i < alter.size(); i++) {
            if (alter.get(i) < min) {
                min = alter.get(i);
            }
        }
        return min;
    }

    public static double minBmi(ArrayList<Double> bmi) {
        Double min = bmi.get(0);
        for (int i = 0; i < bmi.size(); i++) {
            if (bmi.get(i) < min) {
                min = bmi.get(i);
            }
        }
        return min;
    }

    public static double minCharge(ArrayList<Double> charges) {
        Double min = charges.get(0);
        for (int i = 0; i < charges.size(); i++) {
            if (charges.get(i) < min) {
                min = charges.get(i);
            }
        }
        return min;
    }

    public static void statistikAusgebenAlter(float durchschnittAlter, double standardabweichungAlter, int maxAlter, int minAlter) {
        System.out.println("Der Durchschnitt ist: " + durchschnittAlter);
        System.out.println("Die Standardabweichung ist: " + standardabweichungAlter);
        System.out.println("Der Maximalwert ist: " + maxAlter);
        System.out.println("Der Mininalwert ist: " + minAlter);
    }

    public static void statistikAusgebenBmi(float durchschnittBmi, double standardabweichungBmi, double maxBmi, double minBmi) {
        System.out.println("Der Durchschnitt ist: " + durchschnittBmi);
        System.out.println("Die Standardabweichung ist: " + standardabweichungBmi);
        System.out.println("Der Maximalwert ist: " + maxBmi);
        System.out.println("Der Mininalwert ist: " + minBmi);
    }

    public static void statistikAusgebenCharge(float durchschnittCharge, double standardabweichungCharge, double maxCharge, double minCharge) {
        System.out.println("Der Durchschnitt ist: " + durchschnittCharge);
        System.out.println("Die  Standardabweichung ist: " + standardabweichungCharge);
        System.out.println("Der Maximalwert ist: " + maxCharge);
        System.out.println("Der Mininalwert ist: " + minCharge);
    }

    public static void IDSuchen(int id, ArrayList<Integer> alter, ArrayList<String> geschlecht, ArrayList<Double> bmi, ArrayList<Integer> children, ArrayList<Boolean> smoker, ArrayList<String> region, ArrayList<Double> charges) {
        if (id < 0 || id >= alter.size()) {
            System.out.println("Ungültige ID");
        }
        System.out.println("Kunde " + id);
        System.out.println("Alter " + alter.get(id));
        System.out.println("Geschlecht " + geschlecht.get(id));
        System.out.println("BMI " + bmi.get(id));
        System.out.println("Kinder " + children.get(id));
        System.out.println("Raucher " + smoker.get(id));
        System.out.println("Region " + region.get(id));
        System.out.println("Betrag " + charges.get(id));
    }

    public static void kundenLöschen(int id, ArrayList<Integer> alter, ArrayList<String> geschlecht, ArrayList<Double> bmi, ArrayList<Integer> children, ArrayList<Boolean> smoker, ArrayList<String> region, ArrayList<Double> charges) {
        System.out.println("Welchen ID wollen Sie löschen?");

        if (id < 0 || id >= alter.size()) {
            System.out.println("Ungültige ID");
        }
        System.out.println("Kunde " + id + " wird gelöscht.");
        System.out.println("Alter " + alter.get(id));
        System.out.println("Geschlecht " + geschlecht.get(id));
        System.out.println("BMI " + bmi.get(id));
        System.out.println("Kinder " + children.get(id));
        System.out.println("Raucher " + smoker.get(id));
        System.out.println("Region " + region.get(id));
        System.out.println("Betrag " + charges.get(id));

        alter.remove(id);
        geschlecht.remove(id);
        bmi.remove(id);
        children.remove(id);
        smoker.remove(id);
        region.remove(id);
        charges.remove(id);

        System.out.println("Der Kunde wurde erfolgreich gelöscht.");
    }

    public static void kundenEinfügen(ArrayList<Integer> alter, ArrayList<String> geschlecht, ArrayList<Double> bmi, ArrayList<Integer> children, ArrayList<Boolean> smoker, ArrayList<String> region, ArrayList<Double> charges) {
        System.out.println("Neuen Kunden hinzufügen:");

        Scanner sc = new Scanner(System.in);
        System.out.println("Alter: ");
        Integer alterNeu = sc.nextInt();
        System.out.println("Geschlecht: ");
        String geschlechtNeu = sc.next();
        System.out.println("BMI: ");
        Double bmiNeu = sc.nextDouble();
        System.out.println("Kinder: ");
        Integer kinderNeu = sc.nextInt();
        System.out.println("Raucher: ");
        Boolean raucherNeu = sc.nextBoolean();
        System.out.println("Region: ");
        String regionNeu = sc.next();
        System.out.println("Betrag: ");
        Double betragNeu = sc.nextDouble();

        alter.add(alterNeu);
        geschlecht.add(geschlechtNeu);
        bmi.add(bmiNeu);
        children.add(kinderNeu);
        smoker.add(raucherNeu);
        region.add(regionNeu);
        charges.add(betragNeu);

        System.out.println("Neuer Kunde wurde erfolgreich erstellt.");
        System.out.println("Neue ID: " + (alter.size() -1));
    }

    public static void bubbleSort(ArrayList<Integer> alter, ArrayList<String> geschlecht, ArrayList<Double> bmi, ArrayList<Integer> children, ArrayList<Boolean> smoker, ArrayList<String> region, ArrayList<Double> charges) {
        int länge = alter.size();
        boolean getauscht;

        for (int i = 0; i < länge - 1; i++) {
            getauscht = false;

            for (int j = 0; j < länge - 1 - i; j++) {
                if (alter.get(j) > alter.get(j + 1)) {
                    int tempA = alter.get(j);
                    alter.set(j, alter.get(j + 1));
                    alter.set(j + 1, tempA);

                    String tempG = geschlecht.get(j);
                    geschlecht.set(j, geschlecht.get(j + 1));
                    geschlecht.set(j + 1, tempG);

                    getauscht = true;
                }
            }
            if (!getauscht) break;
        }
    }
}