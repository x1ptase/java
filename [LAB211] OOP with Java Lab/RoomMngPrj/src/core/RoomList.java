package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import tool.ConsoleInputter;

public class RoomList extends ArrayList<Room>{
    public static final String FILE_NAME="src\\data\\Active_Room_List.txt";
    
    // kiem tra roomID da ton tai hay chua
    private boolean containsRoomID(String roomID){
        for(Room r : this){
            if(r.getRoomID().equals(roomID)){
                return true;
            }
        }
        return false;
    }
    
    // Function 1
    public void readFromFile(String fName){
        int successCount=0;
        int failureCount=0;
        try{
            FileReader fr=new FileReader(fName);
            BufferedReader bf=new BufferedReader(fr);
            String line;
            while((line = bf.readLine()) != null){
                String[] parts=line.split(";");
                if(parts.length == 6){
                    String roomID=parts[0].trim();
                    String name=parts[1].trim();
                    String type=parts[2].trim();
                    String dailyRateStr=parts[3].trim();
                    String capacityStr=parts[4].trim();
                    String description=parts[5].trim();

                    // check roomID trung lap
                    if(containsRoomID(roomID)){
                        System.out.println("Duplicate RoomID: " + line);
                        failureCount++;
                        continue;
                    }

                    // ngay hop le
                    float dailyRate;
                    try{
                        dailyRate=Float.parseFloat(dailyRateStr);
                        if(dailyRate <= 0){
                            System.out.println("DailyRate must be positive: " + line);
                            failureCount++;
                            continue;
                        }
                    } catch(NumberFormatException e){
                        System.out.println("Invalid DailyRate format: " + line);
                        failureCount++;
                        continue;
                    }

                    // suc chua hop le
                    int capacity;
                    try{
                        capacity=Integer.parseInt(capacityStr);
                        if(capacity <= 0){
                            System.out.println("Capacity must be a positive integer: " + line);
                            failureCount++;
                            continue;
                        }
                    } catch(NumberFormatException e){
                        System.out.println("Invalid Capacity format: " + line);
                        failureCount++;
                        continue;
                    }

                    // them phong hop le
                    this.add(new Room(roomID, name, type, dailyRate, (float) capacity, description));
                    successCount++;
                } else {
                    System.out.println("Invalid line format (requires 6 fields): " + line);
                    failureCount++;
                }
            }
            bf.close();
            fr.close();
        } catch(Exception e){
            System.out.println("Error reading file: " + e.getMessage());
            failureCount++;
        }

        System.out.println(successCount + " rooms successfully loaded.");
        System.out.println(failureCount + " entries failed.");
    } // readFromFile()

    
    // Function 2
    public void displayAll(){
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        System.out.format("%-6s | %-20s | %-9s | %-6s | %-9s | %-30s\n",
                "RoomID", "RoomName", "Type", "Rate", "Capacity", "Furniture");
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        
        for(Room room : this){
            System.out.format("%-6s | %-20s | %-9s | %-6.1f | %-9.2f | %-30s\n",
                    room.getRoomID(),
                    room.getRoomName(),
                    room.getRoomType(),
                    (float)room.getDailyRate(),
                    room.getCapacity(),
                    room.getFurnitureDescription());
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------");
    } // displayAll()

    public Room findRoom(String id){
        int pos=this.indexOf(new Room(id));
        if(pos < 0)
            return null;
        else
            return this.get(pos);
    } // findRoom()

    
    // Function 8
    public RoomList vacantRoomList(GuestList gList) {
        RoomList vaRoomList=new RoomList();
        Date dateCheck=ConsoleInputter.getDate("Enter date want to check (dd/MM/yyyy)", "dd/MM/yyyy");
        // xac thuc ngay nhap vao
        if(dateCheck == null){
            System.out.println("Invalid date input. Returning empty room list.");
            return vaRoomList;
        }

        for(Room r : this){
            // goi isRented voi rentals=1 de kiem tra mot ngay duy nhat
            boolean isRen=r.isRented(gList, dateCheck, 1);
            if(!isRen)
                vaRoomList.add(r);
        }
        // kiem tra danh sach trong
        if(vaRoomList.isEmpty()){
            System.out.println("All rooms have currently been rented out; no rooms are available.");
        }

        return vaRoomList;
    }

    // Function 9
    public void monthlyReport(GuestList gList){
        Date monthYear=ConsoleInputter.getDate("Enter Month Report(MM/yyyy)", "MM/yyyy");
        Calendar cal=Calendar.getInstance();
        cal.setTime(monthYear);
        int targetMonth=cal.get(Calendar.MONTH); // 0-based (0 = January)
        int targetYear=cal.get(Calendar.YEAR);

        ArrayList<String[]> report=new ArrayList<>();
        for(Guest g : gList){
            Room roomRent=this.findRoom(g.getDesiredRoomID());
            if(roomRent == null){
                System.out.println("Warning: Room " + g.getDesiredRoomID() + " not found for guest " + g.getGuestID());
                continue;
        }
            
        float total=0f;
        Date startDate=g.getStartDate();
        int rentalDays=g.getRentalDays();
        Calendar calG=Calendar.getInstance();
        calG.setTime(startDate);

        // kiem tra tung ngay trong khoang thoi gian thue
        for(int i=0; i<rentalDays; i++){
            // lay thang va nam hien tai cua ngay dang kiem tra
            int dayMonth=calG.get(Calendar.MONTH);
            int dayYear=calG.get(Calendar.YEAR);
            if(dayMonth == targetMonth && dayYear == targetYear){
                total+=roomRent.getDailyRate();
            }
            // tang ngay len 1
            calG.add(Calendar.DATE, 1);
        }
        // chi them vao report neu co tien
        if(total > 0){
            report.add(new String[]{
                roomRent.getRoomID(),
                roomRent.getRoomName(),
                roomRent.getRoomType(),
                String.format("%.2f", roomRent.getDailyRate()),
                String.format("%.2f", total)
            });
        }
    }

    if(report.isEmpty())
        System.out.println("No revenue data for " + String.format("%02d/%d", targetMonth + 1, targetYear));
    else
        displayMonthlyReport(report, targetMonth + 1, targetYear);
    
    } // monthlyReport()
    
    private void displayMonthlyReport(ArrayList<String[]> report, int month, int year){
        System.out.println("Monthly Revenue Report - " + String.format("%02d/%d", month, year));
        System.out.println("---------------------------------------------------------------------");
        System.out.printf("%-6s | %-20s | %-9s | %-8s | %-8s%n",
                "RoomID", "Room Name", "Room type", "DailyRate", "Amount");
        System.out.println("---------------------------------------------------------------------");

        for(String[] r : report){
            System.out.printf("%-6s | %-20s | %-9s | %-8s | %-8s%n",
                    r[0], r[1], r[2], r[3], r[4]);
        }
        System.out.println("---------------------------------------------------------------------");
    }

    // Function 10
    public void revenueReport(GuestList gList){
        HashMap<String, Float> revenueMap=new HashMap<>();
        for(Guest guest : gList){
            Room roomRent=this.findRoom(guest.getDesiredRoomID());
            String roomType=roomRent.getRoomType();
            float guestRevenue=guest.getRentalDays() * roomRent.getDailyRate();
            revenueMap.put(roomType, guestRevenue);
        }
        displayRevenueByRoomType(revenueMap);
    }

    private void displayRevenueByRoomType(HashMap<String, Float> revenueByType){
        System.out.println("Revenue Report by Room Type");
        System.out.println("---------------------------");
        System.out.printf("%-12s | %-10s\n", "Room type", "Amount");
        System.out.println("---------------------------");

        if(revenueByType.isEmpty())
            System.out.println("No revenue data available.");
        else
            for(HashMap.Entry<String,Float> entry : revenueByType.entrySet()){
                System.out.printf("%-12s | %-10.2f\n",entry.getKey(), entry.getValue());
            }
        System.out.println("---------------------------");
    }
}//RoomList