package dev.webshop.nailstore.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonNull
	@Column(name = "first_name")
	private String firstName;

	@NonNull
	@Column(name = "last_name")
	private String lastName;

	@NonNull
	@Column(unique = true)
	private String email;

	@NonNull
	@Column(unique = true)
	private String username;

	@NonNull
	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;

	@NonNull
	@Enumerated(EnumType.STRING)
	private Role role;

	@NonNull
	private String password;
}
