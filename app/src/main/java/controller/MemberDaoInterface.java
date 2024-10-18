package controller;

import java.util.List;
import java.time.LocalDate;
import model.*;

public interface MemberDaoInterface { //Using DAO pattern if data source changed in future

  void addMember(String name, String email, String phoneNumber, String password, LocalDate creationDate);

  void deleteMember(String memberId, String password);

  void modifyMember(String memberId);

  void showSpecificMember(String memberId);

  void listSimpleMembersInfo();

  void listAllMembersInfo();

  Member findMemberById(String memberId);

  List<Member> getMembers();

}
