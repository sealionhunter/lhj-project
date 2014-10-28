//package lhj.java.jpa.entity;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "address")
//public class AddressEntity {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;
//	@ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
//    @JoinColumn(name = "userId")
//    @Column(name = "userId")
//	private UserInfoEntity userInfoEntity;
//}
