import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Application {

    private void clearScreen() {
        for (int i = 0; i < 50; ++i) System.out.println();
    }

    private void pressKeyToContinue() {
        System.out.print("\nPress Enter to exist");
        try{ System.in.read(); }
        catch (Exception ignored) {}
    }

    private void addBird(List<Bird> birds) {
        Scanner birdInfo = new Scanner(System.in);

        System.out.print("Bird name: ");
        String birdName = birdInfo.nextLine();
        System.out.print("Bird color: ");
        String birdColor = birdInfo.nextLine();
        System.out.print("Bird weight: ");
        Float birdWeight = birdInfo.nextFloat();
        System.out.print("Bird height: ");
        Float birdHeight = birdInfo.nextFloat();

        if (birds.stream().anyMatch((existedBird) -> Objects.equals(existedBird.getName(), birdName))) {
            System.out.printf("Bird name %s has already existed%n", birdName);
        }

        Bird bird = new Bird(birdName, birdColor, birdWeight, birdHeight);
        birds.add(bird);

        clearScreen();
        System.out.printf("Bird with name %s is added \n", birdName);
        pressKeyToContinue();
        clearScreen();
    }

    private void addSighting(List<Bird> birds, List<Sighting> sightings) {
        Scanner birdInfo = new Scanner(System.in);
        Scanner sightingInfo = new Scanner(System.in);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        System.out.print("Bird name: ");
        String birdName = birdInfo.nextLine();
        System.out.print("Sighting date (dd-MM-yyyy): ");
        String sightingDateStr = sightingInfo.nextLine();
        System.out.print("Sighting location: ");
        String sightingLocation = sightingInfo.nextLine();

        Bird existedBird;
        try {
             existedBird = birds.stream()
                    .filter((bird) -> bird.getName().equals(birdName))
                    .findFirst()
                    .orElseThrow();
        } catch (Exception exception) {
            clearScreen();
            System.out.printf("Bird with name %s not found \n", birdName);
            pressKeyToContinue();
            clearScreen();
            return;
        }

        Date date;
        try {
            date = simpleDateFormat.parse(sightingDateStr);
        } catch (Exception exception) {
            clearScreen();
            System.out.printf("Invalid date format: %S \n", sightingDateStr);
            pressKeyToContinue();
            clearScreen();
            return;
        }

        Sighting sighting = new Sighting(existedBird, sightingLocation, date);
        sightings.add(sighting);

        clearScreen();
        System.out.printf("Sighting for bird: %s is added \n", birdName);
        pressKeyToContinue();
        clearScreen();
    }

    private void removeBird(List<Bird> birds, List<Sighting> sightings) {
        Scanner birdInfo = new Scanner(System.in);

        System.out.print("Bird name: ");
        String birdName = birdInfo.nextLine();

        sightings.removeIf(sighting -> sighting.getBird().getName().equals(birdName));
        birds.removeIf(bird -> bird.getName().equals(birdName));

        clearScreen();
        System.out.printf("Remove bird name %s successfully \n", birdName);
        pressKeyToContinue();
        clearScreen();
    }

    private void printAllBirds(List<Bird> birds) {
        birds.sort(new Comparator<Bird>() {
            @Override
            public int compare(Bird bird1, Bird bird2) {
                return bird1.getName().compareTo(bird2.getName());
            }
        });

        System.out.println("============================================================================================");
        System.out.printf("||  %-10s||  %-15s||  %-15s||  %-15s||  %-15s|| \n","Index", "Bird name", "Bird color", "Bird weight", "Bird height");
        for (int i = 0; i < birds.size(); i++) {
            System.out.printf("||  %-10s||  %-15s||  %-15s||  %-15s||  %-15s|| \n", i+1, birds.get(i).getName(), birds.get(i).getColor(), birds.get(i).getWeight(), birds.get(i).getHeight());
        }
        System.out.println("============================================================================================");

        pressKeyToContinue();
        clearScreen();
    }

    private void printAllSighting(List<Sighting> sightings) {
        Scanner birdInfo = new Scanner(System.in);
        Scanner sightingInfo = new Scanner(System.in);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        System.out.print("Bird name: ");
        String birdName = birdInfo.nextLine();
        System.out.print("Sighting start date (dd-MM-yyyy): ");
        String sightingStartDateStr = sightingInfo.nextLine();
        System.out.print("Sighting end date (dd-MM-yyyy): ");
        String sightingEndDateStr = sightingInfo.nextLine();

        Date startDate;
        Date endDate;
        try {
            startDate = simpleDateFormat.parse(sightingStartDateStr);
            endDate = simpleDateFormat.parse(sightingEndDateStr);
        } catch (Exception exception) {
            clearScreen();
            System.out.println("Invalid date(s) format");
            pressKeyToContinue();
            clearScreen();
            return;
        }

        List<Sighting> sightingList = sightings.stream()
                .filter(sighting -> sighting.getBird().getName().contains(birdName)
                        && (sighting.getTime().equals(startDate)
                        || sighting.getTime().after(startDate)
                        && sighting.getTime().equals(endDate)
                        || sighting.getTime().before(endDate)))
                .collect(Collectors.toList());

        clearScreen();
        System.out.println("============================================================================================");
        System.out.printf("||  %-10s||  %-15s||  %-15s||  %-15s||  %-15s|| \n","Index", "Bird name", "Bird color", "Location", "Sighting date");
        for (int i = 0; i < sightingList.size(); i++) {
            System.out.printf("||  %-10s||  %-15s||  %-15s||  %-15s||  %-15s|| \n", i+1, sightingList.get(i).getBird().getName(), sightingList.get(i).getBird().getColor(), sightingList.get(i).getLocation(), simpleDateFormat.format(sightingList.get(i).getTime()));
        }
        System.out.println("============================================================================================");

        pressKeyToContinue();
        clearScreen();
    }

    public void run() {
        Scanner input = new Scanner(System.in);
        int selection;

        List<Bird> birds = new ArrayList<>();
        List<Sighting> sightings = new ArrayList<>();

        do {
            System.out.println("0. Exist");
            System.out.println("1. Add bird");
            System.out.println("2. Add Sighting");
            System.out.println("3. Remove bird");
            System.out.println("4. Get All Birds");
            System.out.println("5. Get All Sighting");
            System.out.print("Choose you option: ");
            selection = input.nextInt();
            clearScreen();

            switch (selection) {
                case 0:
                    System.out.println("Application Exist !!!");
                    break;
                case 1:
                    addBird(birds);
                    break;
                case 2:
                    addSighting(birds, sightings);
                    break;
                case 3:
                    removeBird(birds, sightings);
                    break;
                case 4:
                    printAllBirds(birds);
                    break;
                case 5:
                    printAllSighting(sightings);
                    break;
                default:
                    break;
            }
        } while (selection != 0);
    }
}
