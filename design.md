# packages-diagramg.
```mermaid

graph RL
    subgraph Package model
    model1[class Time]
    model2[class Member]
    model3[class Item]
    model4[class CategoryEnum]
    model5[class ImmutableContract]
    model6[class FaterOfFunction]
    model7[class SystemManager ]

    end

    subgraph Package controller
    controller1[class TimeDaoImpl]  
    controller2[class TimeDaoInterface]      
    controller3[class MemberDaoInterface] 
    controller4[class MemberDaoImpl]     
    controller5[class ItemDaoInterface]  
    controller6[class ItemDaoImpl]       
    controller7[class ContractDaoInterface] 
    controller8[class ContractDaoImpl]
    controller9[ class Menu]
        
    end

    subgraph Package view
    view1[class MemberViewer]
    view2[class ItemViewer]
    view3[class ContractViewer]
    view4[enum FeedbackMessagesEnum]
    view5[class BaseViewer]
    view6[class Viewer]
    
    end
    controller6 --"implent"--> controller5  
    controller4 --"implent"--> controller3 
    controller8 --"implent"--> controller7  
    controller1 --"implent"--> controller2 

    controller6 --> controller3

    controller9 --> controller3
    controller9 --> controller5
    controller9 --> controller7
    

    controller1 --> model1

    
    controller6 --"depends"--> view2 
    controller4--"depends"--> view1
    controller8 --"depends"--> view3


    controller9 --> view6


    controller4 --> model7
    controller6 --> model7
    controller8 --> model7




    model2 --"owns"--> model3 
    model2 --"have"--> model5 
    model3 --"involved in"--> model5 
    model5 --"lender"--> model2 
    model5 --"borrower"--> model2 

    
    model2 --"extends"--> model6
    model3 --"extends"--> model6
    model5 --"extends"--> model6

    model7 --> model2
    model7 --> model1
    
    view1 --"have"--> view2
    view2 --"have"--> view3


    



    view1 --"extends"--> view5
    view2 --"extends"--> view5
    view3 --"extends"--> view5
    view6 --"extends"--> view5

```




# Class diagram packages model.
```mermaid 
classDiagram
   class FatherOfFunction {
       <<abstract>>
       LocalDate creationDate
       String id
       +String getId()
       void setId()
       LocalDate setCreationDate()
       +LocalDate getCreationDate()
       String generateUniqueId()
       void finalize() throws Throwable
   }


   class Member {
       -String name
       -String email
       -String phoneNumber
       -String password
       -int credits
       -List~Item~ items
       -List~ImmutableContract~ lendingHistory
       +Member(String name, String email, String phoneNumber, String password)
       +void updateMember(String name, String email, String phoneNumber, String password)
       +String getName()
       +void setName(String name)
       +String getEmail()
       +void setEmail(String email)
       +String getPhoneNumber()
       +void setPhoneNumber(String phoneNumber)
       +String getPassword()
       +void setPassword(String password)
       +int getCredits()
       +List~Item~ getItems()
       +List~ImmutableContract~ getLendingHistory()
       +void updateCredits(int amount)
       +boolean isValidEmail(String email)
       +boolean isValidPhoneNumber(String phoneNumber)
       +void addItem(Item item)
       +void removeItem(Item item)
       +void addContract(ImmutableContract contract)
   }


   class Item {
       -CategoryEnum category
       -String name
       -String description
       -int costPerDay
       -Member owner
       -List~ImmutableContract~ lendingHistory
       -boolean isAvailable
       +Item(CategoryEnum category, String name, String description, int costPerDay, Member owner)
       +void updateItem(CategoryEnum category, String name, String description, int costPerDay)
       +CategoryEnum getCategory()
       +void setCategory(CategoryEnum category)
       +String getName()
       +void setName(String name)
       +String getDescription()
       +void setDescription(String description)
       +int getCostPerDay()
       +void setCostPerDay(int costPerDay)
       +Member getOwner()
       +void setOwner(Member owner)
       +List~ImmutableContract~ getContracts()
       +boolean isAvailable()
       +void addContract(ImmutableContract contract)
       +void markAsAvailable()
   }


   class Time {
       -int dayCounter
       +Time(int dayCounter)
       +int getCurrentDay()
       -void setCurrentDay(int dayCounter)
   }


   class ImmutableContract {
       -Member lender
       -Member borrower
       -Item item
       -int startDay
       -int endDay
       -int totalCost
       -String status
       +ImmutableContract(Member lender, Member borrower, Item item, int startDay, int endDay)
       +Member getLender()
       +Member getBorrower()
       +Item getItem()
       +int getStartDay()
       +int getEndDay()
       +int getTotalCost()
       +String getStatus()
       +void completeContract()
       -int calculateTotalCost()
   }


   class CategoryEnum {
       <<enumeration>>
       TOOL
       VEHICLE
       GAME
       TOY
       SPORT
       OTHER
   }

   class SystemManager {
    -List ~Member~ member
    +void addMembers(String name, String email, String password, String phonenumber)
    +void updateMember(String[] memberInfo)
    +void removeMember(String[] memberInfo)
    +List<Member> getMembers()
    +Member getMemberById(String memberId)
    +void updateItem(Member member, Item item, String[] itemInfo)
    +Item getItemById(Member member, String itemId)
    +void validateMemberDetails(String name, String email, String password, String phoneNumber)
    +void checkUnique(String email, String phoneNumber)
   }


   %% Relationships
   Member "1" -- "0..*" Item : owns >
   Member "1" -- "0..*" ImmutableContract : have >
   Item "1" -- "0..*" ImmutableContract : is involved in >
   ImmutableContract "1" -- "1" Member : lender >
   ImmutableContract "1" -- "1" Member : borrower >
   SystemManager "1" --"0..*" Member : manage member stuff >
   SystemManager "1" --"0..*" Time : get current time >


   %% Inheritance
   FatherOfFunction <|-- Member
   FatherOfFunction <|-- Item
   FatherOfFunction <|-- ImmutableContract

```






# Class diagram package controller
```mermaid
classDiagram

    %% TimeDaoInterface and TimeDaoImpl
    class TimeDaoInterface {
        <<Interface>> 
        +int getCurrentDay()
        +void advanceDay()
        +void advanceDays(int days)
    }
    
    class TimeDaoImpl {
        -int dayCounter
        -Time time
        +void advanceDay()
        +void advanceDays(int numberOfDays)
        +int getCurrentDay()
    }
    TimeDaoInterface <|.. TimeDaoImpl

    %% MemberDaoInterface and MemberDaoImpl
    class MemberDaoInterface {
        <<Interface>> 
        +void addMember(String name, String email, String phoneNumber, String password)
        +void modifyMember(String memberId, String name, String email, String phoneNumber, String password)
        +void deleteMember(String memberId, String password)
        +void checkUnique(String email, String phoneNumber)
        +Member showSpecificMemberInfo(String memberId)
        +Member getMemberById(String memberId)
        +List~Member~ getMembers()
        +List~Item~ getAvilbaleItems()
    }
    
    class MemberDaoImpl {
        -List~Member~ members
        +void addMember(String name, String email, String phoneNumber, String password)
        +void modifyMember(String memberId, String name, String email, String phoneNumber, String password)
        +void deleteMember(String memberId, String password)
        +void checkUnique(String email, String phoneNumber)
        +Member showSpecificMemberInfo(String memberId)
        +Member getMemberById(String memberId)
        +List~Member~ getMembers()
        +List~Item~ getAvilbaleItems()
    }
    MemberDaoInterface <|.. MemberDaoImpl


    %% ItemDaoInterface and ItemDaoImpl
    class ItemDaoInterface {
        <<Interface>> 
        +void createItem(String memberId, CategoryEnum category, String name, String description, int costPerDay)
        +void modifyItem(String memberId, String itemId, CategoryEnum category, String name, String description, int costPerDay)
        +void deleteItem(String memberId, String itemId)
        +Item viewItem(String memberId, String itemId)
        +List~Item~ getItemsByMember(String memberId)
    }
    
    class ItemDaoImpl {
        -MemberDaoInterface memberDao
        +void createItem(String memberId, CategoryEnum category, String name, String description, int costPerDay)
        +void modifyItem(String memberId, String itemId, CategoryEnum category, String name, String description, int costPerDay)
        +void deleteItem(String memberId, String itemId)
        +Item viewItem(String memberId, String itemId)
        +List~Item~ getItemsByMember(String memberId)
    }
    ItemDaoImpl ..|>  ItemDaoInterface
    ItemDaoImpl "1"-- "1" MemberDaoInterface : belong to

    %% ContractDaoInterface and ContractDaoImpl
    class ContractDaoInterface {
        <<Interface>> 
        +void createContract(Member lender, Member borrower, Item item, int startDay, int endDay)
        +boolean isItemAvailableToLend(Item item)
        +boolean isEnoughFundsToBorrow(int borrowerFunds, int itemCost)
    }
    
    class ContractDaoImpl {
        -TimeDaoInterface timeDao
        +void createContract(Member lender, Member borrower, Item item, int startDay, int endDay)
        +boolean isItemAvailableToLend(Item item)
        +boolean isEnoughFundsToBorrow(int borrowerFunds, int itemCost)
    }
    ContractDaoInterface <|.. ContractDaoImpl

    class Menu {
        -MemberDaoInterface memberDao
        -ItemDaoInterface itemDao
        -ContractDaoInterface contractDao
        -Viewer viewer
        +void mainMenu()
        -void memberMenu()
        -void contractMenu()
        -void itemMenu()
    }
    
    Menu "1"-- "0..*" MemberDaoInterface : have member menu 
    Menu "1"-- "0..*" ItemDaoInterface : have item menu
    Menu "1"-- "0..*" ContractDaoInterface : have contact 
    
````



# Class diagram package view.
```mermaid
classDiagram
    class Baseviewer {
        <<abstract>>
        String promptForInput(String message)
        int promptForInt(String message)
        void waitForUserInput()
        +void displayFeedback(boolean success, String successMsg, String errorMsg)
    }

    class enum FeedbackMessage {
        +enum FeedbackMessage
        +String getMessage()
    }   
    class Viewer {
        String promptForInput(String message)
        int promptForInt(String message)
        void waitForUserInput()
        void displayFeedback(boolean success, String successMsg, String errorMsg)
    }
    class MemberViewer {
        -ItemViewer itemViewer
        +void createMember()
        +void editMemberInfo()
        +void deleteMember()
        +void specificMemberFullInfo()
        +void displayMembersOverview()
        +void displayMembersWithDetailedItems()
        +void getAvilbaleItems()
        +void findMember(List<Member> members)
    }

    class ItemViewer {
        -ContractViewer contractViewer;
        +void viewItems(Member member)
        +void viewAvailableItems(List<Item> items)
        +void editIteminfo()
        +void addNewItem()
        +void deleteItem()
        -void displayItemInfo(Item item)
    }

    class ContractViewer {
        +String[] createContract()
        +void viewContract(Item item)

    }
    MemberViewer "1"--"0..*"  ItemViewer 
    ItemViewer  "1"--"0..*"  ContractViewer 
    Baseviewer <|-- Viewer
    Baseviewer <|-- ItemViewer
    Baseviewer <|-- ContractViewer
    Baseviewer <|-- MemberViewer

   
```


# SequenceDiagram

```mermaid
sequenceDiagram
    participant App as App 
    participant Menu as Controller <br/> Menu
    participant MC as Controller <br/> MemberDaoImpl 
    participant MV as Viewer <br/> MemberViewer
    participant SM as  Model <br/> SystemManager
    participant M1 as exixting member <br/> Member1
    participant M1 as exixting member <br/> Member1
    participant M as Model <br/> Member
    
    %% Scenario: Add a new third member with user input and database interaction
    App->> Menu: mainMenu()
    Menu->>Menu: memberMenu()
    Menu->>MC: createMember()
    MC->>+MV: prumpt for createMember()
    MV-->>-MC: return a String[] of member info
    MC->>+SM: addMember(name, email, phoneNumber, password) 
    SM->>SM: validieteMemberDetails(name, email, phoneNumber, password) 
    SM->>M: Member(name, email, phoneNumber, password)
    SM->>SM: store the member in members list
    SM-->>-MC: member added successfully
    MC->>MV: Display feedback

````



# ObjectDiagram
![object_diagram](img/object_diagram.png)



    