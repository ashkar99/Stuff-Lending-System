package controller;

import java.util.List;
import java.time.LocalDate;
import model.*;

public interface MemberDaoInterface { // Using DAO pattern if data source changed in future

  void addMember(String name, String email, String phoneNumber, String password, LocalDate creationDate);

  void deleteMember(String memberId, String password);

  void modifyMember(String memberId, String name, String email, String phoneNumber, String password);

  void checkUnique(String email, String phoneNumber);

  Member showSpecificMemberInfo(String memberId);

  List<Member> listSimpleMembersInfo();

  List<Member> listAllMembersInfo();

  Member findMemberById(String memberId);

  List<Member> getMembers();

}
