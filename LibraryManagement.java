import java.util.Scanner;
import java.util.Vector;

class Library {
    protected Vector<String> bookName = new Vector<>();
    protected Vector<String> authorName = new Vector<>();
    protected Vector<Integer> quantityOfBook = new Vector<>();
    protected Vector<String> studentName = new Vector<>();
    protected int countId = 100;
    protected Vector<Integer> studId = new Vector<>();
    protected Vector<String> MobNo = new Vector<>();

    public void addNewBooks(Scanner sc) {
        sc.nextLine();
        System.out.print("Enter the Books Name: ");
        bookName.addElement(sc.nextLine());
        System.out.print("Enter Author Name: ");
        authorName.addElement(sc.nextLine());
        System.out.print("Enter Quantity of Books: ");
        quantityOfBook.addElement(sc.nextInt());
        System.out.println("Book Added in Libray Record...");
        SortBooks();
    }

    public void showAvailabeBooks() {
        if (!bookName.isEmpty()) {
            System.out.println("Currently Available Books : ");
            for (int i = 0; i < bookName.size(); i++) {
                System.out.println((i + 1) + ". " + bookName.elementAt(i) + "\t~Author Name " + authorName.elementAt(i)
                        + "\t~available quantity of Book is " + quantityOfBook.elementAt(i));
            }
        } else {
            System.out.println("Books are not available in Library!!!");
        }
    }

    public void borrowBoook(Scanner sc) {
        if (!bookName.isEmpty()) {
            System.out.println("Enter your Student ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            boolean registered = false;
            for (int j = 0; j < studId.size(); j++) {
                if (id == studId.elementAt(j)) {
                    String key;
                    System.out.print("Enter Book Name : ");
                    key = sc.nextLine();
                    int i = searchBook(sc, key);
                    if (i != -1) {
                        System.out.println("Book Details:- ");
                        System.out.println(bookName.elementAt(i) + "\t~Author name " + authorName.elementAt(i)
                                + "\t~available quantity of Book is " + quantityOfBook.elementAt(i));
                        System.out.print("Enter Quanitiy of Book you want to borrow: ");
                        int b = sc.nextInt();
                        if (b <= quantityOfBook.elementAt(i)) {
                            quantityOfBook.set(i, quantityOfBook.elementAt(i) - b);
                            System.out.println("Book Boorrowed Successfully...");
                            registered = true;
                        } else {
                            System.out.println("Insufficient quantity available!");
                        }
                    }
                    break;
                }
            }
            if (!registered) {
                System.out.println("Sorry, you are not a registered student.");
                System.out.println("Please register first to borrow books.");
            }
        } else {
            System.out.println("Books are not available in Library!!!");
        }
    }

    public int searchBook(Scanner sc, String key) {
        int mid, u, l;
        u = bookName.size() - 1;
        l = 0;
        while (l <= u) {
            mid = (l + u) / 2;
            int compare = key.compareToIgnoreCase(bookName.elementAt(mid));
            if (compare == 0) {
                System.out.println("Book is found at position " + (mid + 1));
                return mid;
            } else if (compare > 0) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        System.out.println("Book is not Available Currently!!!");
        return -1;
    }

    public void SortBooks() {
        int bsize = bookName.size();
        String temp = null;
        for (int i = 0; i < bsize - 1; i++) {
            for (int j = 0; j < bsize - 1 - i; j++) {
                if (bookName.elementAt(j).compareToIgnoreCase(bookName.elementAt(j + 1)) > 0) {
                    temp = bookName.elementAt(j);
                    bookName.set(j, bookName.elementAt(j + 1));
                    bookName.set(j + 1, temp);
                }
            }
        }
    }

    public void showAllRegisteredStudentId() {
        if (!studId.isEmpty()) {
            System.out.println("Registered Students ID: ");
            int i = 0;
            for (int val : studId) {
                System.out.printf("%d. %d    ", (i + 1), (val));
                i++;
            }
            System.out.println();
        } else {
            System.out.println("No Students are Registered Currently!!!");
        }
    }

    public void registeringStudent(Scanner sc) {
        sc.nextLine();
        System.out.print("Enter Your Name: ");
        studentName.addElement(sc.nextLine());
        System.out.print("Enter Mobile Number: ");
        MobNo.addElement(sc.nextLine());
        countId++;
        studId.addElement(countId);
        System.out.println("Your Registeration is Completed..");
        System.out.println("Your Student ID is " + countId);
    }

    public void studentInfo(Scanner sc) {
        if (!studId.isEmpty()) {
            System.out.print("Enter Student ID for INFO: ");
            int id = sc.nextInt();
            for (int i = 0; i < studId.size(); i++) {
                if (id == studId.elementAt(i)) {
                    System.out.println("Name: " + studentName.elementAt(i));
                    System.out.println("Student ID: " + studId.elementAt(i));
                    System.out.println("Mobile No: " + MobNo.elementAt(i));
                }
            }
        } else {
            System.out.println("No Students are Registered Currently!!!");
        }
    }
}

public class LibraryManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library l = new Library();
        int ch;
        String key;
        do {
            System.out.println("************************************************************");
            System.out.println("***************<< Welcome to GEETA LIBRARY >>***************");
            System.out.println("**********<< Select from the following Options >>***********");
            System.out.println("************************************************************");
            System.out.println(
                    "Press 1 to Show Available Books.\nPress 2 to Search Book.\nPress 3 to Add Books.\nPress 4 to Register Student.\nPress 5 to Show all the Registered Students.\nPress 6 to Show Students Info.\nPress 7 to Borrow a Book.\nPress 8 to Exit Application.");
            System.out.println("************************************************************");
            ch = sc.nextInt();
            switch (ch) {
                case 1:
                    l.showAvailabeBooks();
                    System.out.println();
                    break;
                case 2:
                    if (!l.bookName.isEmpty()) {
                        sc.nextLine();
                        System.out.println("Enter Book Name : ");
                        key = sc.nextLine();
                        l.searchBook(sc, key);
                    } else {
                        System.out.println("Books are not available in Library!!!");
                    }
                    System.out.println();
                    break;
                case 3:
                    l.addNewBooks(sc);
                    System.out.println();
                    break;
                case 4:
                    l.registeringStudent(sc);
                    System.out.println();
                    break;
                case 5:
                    l.showAllRegisteredStudentId();
                    System.out.println();
                    break;
                case 6:
                    l.studentInfo(sc);
                    System.out.println();
                    break;
                case 7:
                    l.borrowBoook(sc);
                    System.out.println();
                    break;
                case 8:
                    System.out.println("Thank you for using Our Application!..");
                    System.out.println();
                    break;
                default:
                    System.out.println("Wrong Option!!!");
                    System.out.println();
            }
        } while (ch != 8);
    }
}