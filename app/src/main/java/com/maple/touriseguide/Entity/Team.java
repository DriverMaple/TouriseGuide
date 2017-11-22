package com.maple.touriseguide.Entity;

/**
 * Created by Maple on 2017/11/16.
 */
public class Team {
    private Integer team_id;
    private String team_name;
    private String team_pw;
    private Integer user_id;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Integer team_id) {
        this.team_id = team_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getTeam_pw() {
        return team_pw;
    }

    public void setTeam_pw(String team_pw) {
        this.team_pw = team_pw;
    }
}
