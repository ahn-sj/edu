package org.zerock.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//setter주입
//생성자 주입
//필드 주입
@Component
@Data
@RequiredArgsConstructor
public class Restaurant {
//	@Autowired
//	@Setter(onMethod_ = {@Autowired})
	private final Chef chef;
}
