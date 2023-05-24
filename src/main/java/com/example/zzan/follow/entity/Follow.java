package com.example.zzan.follow.entity;

import com.example.zzan.follow.dto.FollowRuquestDto;
import com.example.zzan.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Follow {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name ="following_id",nullable = false)
	private User followingId;

	@ManyToOne
	@JoinColumn(name = "follower_id", nullable = false)
	private User followerId;


	public Follow(User followingId, User followerId){
		this.followingId = followingId;
		this.followerId = followerId;
	}
}