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
    controller9[class FeedbackMessagesEnum]
        
    end

    subgraph Package view
    view1[class MemberViewer]
    view2[class ItemViewer]
    view3[class ContractViewer]
    
    end

    controller6 --> controller3
    controller8 --> controller1


    model2 --"owns"--> model3 
    model2 --"have"--> model5 
    model3 --"involved in"--> model5 
    model5 --"lender"--> model2 
    model5 --"borrower"--> model2 

    
    model2 --"Inheritance"--> model6
    model3 --"Inheritance"--> model6
    model5 --"Inheritance"--> model6
    
    view1 --> view2
    view2 --> view3

    controller6 --"implent"--> controller5  
    controller4 --"implent"--> controller3 
    controller8 --"implent"--> controller7  
    controller1 --"implent"--> controller2 
    

    controller1 --> model1
    controller4 --> model2
    
    view2 --> controller6
    view1 --> controller4
```




# Class diagram packages model.
```mermaid 
classDiagram
   class FatherOfFunction {
       <<abstract>>
       LocalDate creationDate
       String id
       String getId()
       void setId()
       LocalDate setCreationDate()
       LocalDate getCreationDate()
       String generateUniqueId()
       void finalize() throws Throwable
   }


   class Member {
       -String name
       -String email
       -String phoneNumber
       -String password
       -int credits
       +List~Item~ items
       +List~ImmutableContract~ lendingHistory
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
       +CategoryEnum category
       +String name
       +String description
       +int costPerDay
       +Member owner
       +List~ImmutableContract~ lendingHistory
       +boolean isAvailable
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
       +int dayCounter
       +Time(int dayCounter)
       +int getCurrentDay()
       +void setCurrentDay(int dayCounter)
   }


   class ImmutableContract {
       +Member lender
       +Member borrower
       +Item item
       +int startDay
       +int endDay
       +int totalCost
       +String status
       +ImmutableContract(Member lender, Member borrower, Item item, int startDay, int endDay)
       +Member getLender()
       +Member getBorrower()
       +Item getItem()
       +int getStartDay()
       +int getEndDay()
       +int getTotalCost()
       +String getStatus()
       +void completeContract()
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


   %% Relationships
   Member "1" -- "0..*" Item : owns >
   Member "1" -- "0..*" ImmutableContract : have >
   Item "1" -- "0..*" ImmutableContract : is involved in >
   ImmutableContract "1" -- "1" Member : lender >
   ImmutableContract "1" -- "1" Member : borrower >


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
    ItemDaoImpl --> MemberDaoInterface

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
    TimeDaoInterface <-- ContractDaoImpl

````



# Class diagram package view.
```mermaid
classDiagram
    class MemberViewer {
        +void mainMenu()
        +void createMember()
        +void editMemberInfo()
        +void deleteMember()
        +void specificMemberFullInfo()
        +void displayMembersOverview()
        +void displayMembersWithDetailedItems()
        +void getAvilbaleItems()
    }

    class ItemViewer {
        +void viewItems(Member member)
        +void viewAvailableItems(List<Item> items)
        +void editIteminfo()
        +void addNewItem()
        +void deleteItem()
    }

    class ContractViewer {
        +void viewContract(Item item)
    }
    MemberViewer  -->  ItemViewer
    ItemViewer  --> ContractViewer

   
```


# SequenceDiagram

```mermaid
sequenceDiagram
    participant MV as MemberViewer
    participant MC as MemberDaoImpl
    participant M as Member

    %% Scenario: Add a new third member with user input and database interaction
    MV->>MV: prompt for name, email, password, phoneNumber
    MV->>MC: createMember(name, email, phoneNumber, password)  %% Send data to controller

    MC->>M: addMember(name, email, phoneNumber, password)   %% Controller calls Model to add new member
    M-->>MC: return new Member object with memberId, name, email, creationDate, password.  %% Member is created and returned to Controller

    MC->>MC: store member data in list of members  %% Controller handles saving the new member
    
    MC-->>MV: return a copy of Member object to view
    MV->>MV: display member details to user
````

    