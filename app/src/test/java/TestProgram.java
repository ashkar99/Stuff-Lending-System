import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import controller.*;
import model.*;
import java.util.List;

/**
 * TestProgram to verify the functionality of the lending system based on the
 * test cases.
 */
public class TestProgram {

    private MemberDaoImpl memberDao;
    private ItemDaoImpl itemDao;
    private ContractDaoImpl contractDao;
    private TimeDaoImpl timeDao;

    private String m1Id;
    private String m2Id;
    private String m3Id;
    private String i1Id;
    private String i2Id;

    @BeforeEach
    public void setup() {
        memberDao = new MemberDaoImpl();
        itemDao = new ItemDaoImpl(memberDao);
        contractDao = new ContractDaoImpl();
        timeDao = new TimeDaoImpl();

        // Create initial members
        Member m1 = new Member("M1", "m1@example.com", "1234506789", "password");
        Member m2 = new Member("M2", "m2@example.com", "9876543210", "password");
        Member m3 = new Member("M3", "m3@example.com", "1122434455", "password");
        memberDao.addMembers(m1);
        memberDao.addMembers(m2);
        memberDao.addMembers(m3);

        // Print members to check if they exist
        List<Member> members = memberDao.getMembers();
        System.out.println("Number of members: " + members.size());
        for (Member member : members) {
            System.out.println("Member ID: " + member.getId() + " Name: " + member.getName());
        }

        // Retrieve their generated IDs
        m1Id = m1.getId();
        m2Id = m2.getId();
        m3Id = m3.getId();
        m1.updateCredits(300);  //300 + 200 criedts from new added items
        m2.updateCredits(100);
        m3.updateCredits(100);
        // M1 adds two items
        itemDao.createItem(m1Id, CategoryEnum.TOOL, "I1", "Expensive Tool", 50);
        itemDao.createItem(m1Id, CategoryEnum.GAME, "I2", "Cheap Game", 10);

        // Retrieve their generated item IDs
        i1Id = memberDao.getMemberById(m1Id).getItems().get(0).getId();
        i2Id = memberDao.getMemberById(m1Id).getItems().get(1).getId();

        // M3 has an active lending contract for I2 starting day 5 and ending day 7
        contractDao.createContract(memberDao.getMemberById(m1Id), memberDao.getMemberById(m3Id),
                itemDao.getItemById(memberDao.getMemberById(m1Id), i2Id), 5, 7);
    }

    @Test
    public void testInitialMemberData() {
        // Test case: 5.1 Member Data
        List<Member> members = memberDao.getMembers();
        assertEquals(3, members.size(), "There should be 3 members");

        // Check M1 details
        Member m1 = memberDao.getMemberById(m1Id);
        assertEquals(530, m1.getCredits(), "M1 should have 500 credits + 30 from lending item 2");
        assertEquals(2, m1.getItems().size(), "M1 should have 2 items for lending");

        // Check M2 details
        Member m2 = memberDao.getMemberById(m2Id);
        assertEquals(100, m2.getCredits(), "M2 should have 100 credits");
        assertEquals(0, m2.getItems().size(), "M2 should have no items for lending");

        // Check M3 details
        Member m3 = memberDao.getMemberById(m3Id);
        assertEquals(70, m3.getCredits(), "M3 should have 100 credits");
        List<ImmutableContract> m3Contracts = itemDao.getItemById(memberDao.getMemberById(m1Id), i2Id).getContracts();
        assertFalse(m3Contracts.isEmpty(), "M3 should have an active contract for I2");
    }

    @Test
    public void testCreateMember() {
        // Test case: 1.1 Create Member
        memberDao.addMember("Allan Turing", "allan@enigma.com", "1234567890", "password");
        Member member = memberDao.getMembers().get(memberDao.getMembers().size() - 1); // Get the last member added
        assertNotNull(member, "Member 'Allan Turing' should be created");
        assertEquals("allan@enigma.com", member.getEmail(), "Email should match");
    }

    @Test
    public void testCreateDuplicateMember() {
        // Test case: 1.2 Create Member - Duplicate Email and Phone
        memberDao.addMember("Allan", "allan@enigma.com", "1234567809", "password");
        assertThrows(IllegalArgumentException.class, () -> {
            memberDao.addMember("Turing", "allan@enigma.com", "1239999999", "password");
        }, "Should throw exception for duplicate email");

        assertThrows(IllegalArgumentException.class, () -> {
            memberDao.addMember("Turing", "turing@enigma.com", "1234567809", "password");
        }, "Should throw exception for duplicate phone number");
    }

    @Test
    public void testDeleteMember() {
        // Test case: 1.3 Delete Member
        memberDao.addMember("Allan", "allan@enigma.com", "1234560789", "password");
        Member member = memberDao.getMembers().get(memberDao.getMembers().size() - 1);
        assertNotNull(member, "Member should be created");

        memberDao.deleteMember(member.getId(), "password");
        assertNull(memberDao.getMemberById(member.getId()), "Member should be deleted");
    }

    @Test
    public void testCreateItem() {
        // Test case: 2.1 Create Item
        memberDao.addMember("John", "john@example.com", "0987657890", "password");
        String johnId = memberDao.getMembers().get(memberDao.getMembers().size() - 1).getId();
        itemDao.createItem(johnId, CategoryEnum.TOY, "Toy Car", "Remote Control Car", 15);
        Member john = memberDao.getMemberById(johnId);
        assertEquals(1, john.getItems().size(), "John should have 1 item");
        assertEquals(100, john.getCredits(), "John should have earned 100 credits");
    }

    @Test
    public void testDeleteItem() {
        // Test case: 2.2 and 2.3 Delete Item
        Item item = itemDao.getItemById(memberDao.getMemberById(m1Id), i2Id);
        itemDao.deleteItem(memberDao.getMemberById(m1Id).getId(), item.getId());
        assertFalse(memberDao.getMemberById(m1Id).getItems().contains(item), "Item should be deleted");
    }

    @Test
    public void testCreateContract() {
        // Test case: 3.1 Create Contract
        contractDao.createContract(memberDao.getMemberById(m1Id), memberDao.getMemberById(m2Id),
                itemDao.getItemById(memberDao.getMemberById(m1Id), i2Id), 1, 3);
        assertTrue(itemDao.getItemById(memberDao.getMemberById(m1Id), i2Id).isAvailable(),
                "Item I2 should be unavailable");
    }

    // Test for insufficient funds (3.2)
    @Test
    public void testCreateContractWithNotEnoughFunds() {
        // M2 has only 100 credits, and I1 costs 50 credits per day, making a 3-day
        // rental cost 150
        assertThrows(IllegalArgumentException.class, () -> {
            contractDao.createContract(memberDao.getMemberById(m1Id), memberDao.getMemberById(m2Id),
                    itemDao.getItemById(memberDao.getMemberById(m1Id), i1Id), 1, 3);
        }, "Should throw exception due to insufficient funds.");
    }

    // Test for conflicting time (3.3) - Day 4 to Day 6
    @Test
    public void testCreateContractConflictingTime_4to6() {
        // M2 tries to borrow I2 (already borrowed by M3 from day 5 to day 7)
        assertThrows(IllegalArgumentException.class, () -> {
            contractDao.createContract(memberDao.getMemberById(m1Id), memberDao.getMemberById(m2Id),
                    itemDao.getItemById(memberDao.getMemberById(m1Id), i2Id), 4, 6);
        }, "Should throw exception due to conflicting time.");
    }

    // Test for conflicting time (3.4) - Day 6 to Day 9
    @Test
    public void testCreateContractConflictingTime_6to9() {
        // M2 tries to borrow I2 (already borrowed by M3 from day 5 to day 7)
        assertThrows(IllegalArgumentException.class, () -> {
            contractDao.createContract(memberDao.getMemberById(m1Id), memberDao.getMemberById(m2Id),
                    itemDao.getItemById(memberDao.getMemberById(m1Id), i2Id), 6, 9);
        }, "Should throw exception due to conflicting time.");
    }

    // Test for conflicting time (3.5) - Day 4 to Day 9
    @Test
    public void testCreateContractConflictingTime_4to9() {
        // M2 tries to borrow I2 (already borrowed by M3 from day 5 to day 7)
        assertThrows(IllegalArgumentException.class, () -> {
            contractDao.createContract(memberDao.getMemberById(m1Id), memberDao.getMemberById(m2Id),
                    itemDao.getItemById(memberDao.getMemberById(m1Id), i2Id), 4, 9);
        }, "Should throw exception due to conflicting time.");
    }

    // Test for conflicting time (3.6) - Day 6 to Day 6
    @Test
    public void testCreateContractConflictingTime_6to6() {
        // M2 tries to borrow I2 (already borrowed by M3 from day 5 to day 7)
        assertThrows(IllegalArgumentException.class, () -> {
            contractDao.createContract(memberDao.getMemberById(m1Id), memberDao.getMemberById(m2Id),
                    itemDao.getItemById(memberDao.getMemberById(m1Id), i2Id), 6, 6);
        }, "Should throw exception due to conflicting time.");
    }

    @Test
    public void testAdvanceTime() {
        // Test case: 4.1 Advance time
        timeDao.advanceDays(8);
        assertEquals(8, timeDao.getCurrentDay(), "Time should have advanced to day 8");

        Member m3 = memberDao.getMemberById(m3Id);
        assertEquals(70, m3.getCredits(), "M3 should now have 70 credits after contract fulfillment");
    }
}
