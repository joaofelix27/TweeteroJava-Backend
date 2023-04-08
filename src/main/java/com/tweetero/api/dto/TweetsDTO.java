package com.tweetero.api.dto;

import java.net.URL;

public record TweetsDTO(String username,
URL avatar, String tweet)
{

}
