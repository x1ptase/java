package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import tool.ConsoleInputter;

public class RoomList extends ArrayList<Room> {

    public static String fName = "src\\data\\Active_Room_List.txt";

    public void readFile(String fName) {
        try {
            FileReader fr = new FileReader(fName);
            BufferedReader bf = new BufferedReader(fr);
            String line;
            while ((line = bf.readLine()) != null) {
                String parts[] = line.split(";");
                if (parts.length == 6) {
                    String roomID = parts[0];
                    if (!containsRoomID(roomID)) {

                        String name = parts[1];
                        String type = parts[2];
                        float dailyRate = Float.parseFloat(parts[3]);
                        float capacity = Float.parseFloat(parts[4]);
                        String description = parts[5];
                        this.add(new Room(roomID, name, type, dailyRate, capacity, description));
                    }

                }
            }
            fr.close();
            bf.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private boolean containsRoomID(String roomID) {
        for (Room r : this) {
            if (r.getRoomID().equals(roomID)) {
                return true;
            }
        }
        return false;
    }

    public void displayAll() {
        System.out.println("------------------------------------------------------------------------------------------------------------------");

        System.out.format("%-6s | %-20s | %-9s | %-6s | %-9s | %-30s\n",
                "RoomID", "RoomName", "Type", "Rate", "Capacity", "Furniture");

        System.out.println("------------------------------------------------------------------------------------------------------------------");

        for (Room room : this) {
            System.out.format("%-6s | %-20s | %-9s | %-6.1f | %-9.2f | %-30s\n",
                    room.getRoomID(),
                    room.getRoomName(),
                    room.getRoomType(),
                    (float) room.getDailyRate(),
                    room.getCapacity(),
                    room.getFunrnitureDescription());
        }

        System.out.println("------------------------------------------------------------------------------------------------------------------");
    }

    public Room findRoom(String id) {
        int pos = this.indexOf(new Room(id));
        if (pos < 0) {
            return null;
        } else {
            return this.get(pos);
        }

    }

    public RoomList vacantRoomList(GuestList gList) {
        RoomList vaRoomList = new RoomList();
        boolean isRen;
        Date dateCheck =  ConsoleInputter.getDate("Enter date want to check(dd/MM/yyyy)", "dd/MM/yyyy");
        for (Room r : this) {
            isRen = r.isRented(gList, dateCheck);
            if (!isRen) {
                vaRoomList.add(r);
            }
        }

        return vaRoomList;
    }

    public void monthlyReport(GuestList gList) {
        
        Date monthYear = ConsoleInputter.getDate("Enter Month Report(MM/yyyy)", "MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.setTime(monthYear);
        int targetMonth = cal.get(Calendar.MONTH);
        int targetYear = cal.get(Calendar.YEAR);

        ArrayList<String[]> report = new ArrayList<>();
        for (Guest g : gList) {
            float total = 0f;
            Date startDate = g.getStartDate();
            int rentalDays = g.getRentalDays();
            Room roomRent = this.findRoom(g.getRoomID());
            Calendar calG = Calendar.getInstance();
            calG.setTime(startDate);
            for (int i = 0; i < rentalDays; i++) {
                int dayGuest = calG.get(Calendar.DATE);
                int dayMonth = calG.get(Calendar.MONTH);
                int dayYear = calG.get(Calendar.YEAR);
                calG.add(dayGuest, i);
                if (dayMonth == targetMonth && dayYear == targetYear) {
                    total += roomRent.getDailyRate();
                }
            }
            report.add(new String[]{
                roomRent.getRoomID(), roomRent.getRoomName(), roomRent.getRoomType(),
                String.format("%.2f", roomRent.getDailyRate()), String.format("%.2f", total)
            });
        }
        displayMonthlyReport(report, targetMonth + 1, targetYear);
    }

    private void displayMonthlyReport(ArrayList<String[]> report, int month, int year) {
        System.out.println("Monthly Revenue Report - " + String.format("%02d/%d", month, year));
        System.out.println("---------------------------------------------------------------------");
        System.out.printf("%-6s | %-20s | %-9s | %-8s | %-8s%n",
                "RoomID", "Room Name", "Room type", "DailyRate", "Amount");
        System.out.println("---------------------------------------------------------------------");

        for (String[] r : report) {
            System.out.printf("%-6s | %-20s | %-9s | %-8s | %-8s%n",
                    r[0], r[1], r[2], r[3], r[4]);
        }
        System.out.println("---------------------------------------------------------------------");
    }

    public void revenueReport(GuestList gList) {
        HashMap<String, Float> revenueMap = new HashMap<>();
        for (Guest guest : gList) {
            Room roomRent = this.findRoom(guest.getRoomID());
            String roomType = roomRent.getRoomType();
            float guestRevenue = guest.getRentalDays() * roomRent.getDailyRate();
            revenueMap.put(roomType, guestRevenue);
        }
        displayRevenueByRoomType(revenueMap);
    }

    private void displayRevenueByRoomType(HashMap<String, Float> revenueByType) {
        System.out.println("Revenue Report by Room Type");
        System.out.println("---------------------------");
        System.out.printf("%-12s | %-10s\n", "Room type", "Amount");
        System.out.println("---------------------------");

        if (revenueByType.isEmpty()) {
            System.out.println("No revenue data available.");
        } else {
            for(HashMap.Entry<String,Float> entry : revenueByType.entrySet()) {
                System.out.printf("%-12s | %-10.2f\n",entry.getKey(), entry.getValue());
            }
        }
        System.out.println("---------------------------");
    }
}//RoomList
