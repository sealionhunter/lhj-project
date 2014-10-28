//package lhj.java.jpa.entity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "userInfo")
//public class UserInfoEntity {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;
//	@OneToMany(mappedBy = "userInfoEntity", fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH })
//	private List<AddressEntity> addressList = new ArrayList<AddressEntity>();
//}
