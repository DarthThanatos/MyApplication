package com.example.vobis.myapplication.trail;

/**
 * Created by Vobis on 2015-08-03.
 */
public class Constant {
    static String[] state = {"Games","Films","Books"};
    static  String[][] parent = {
            {"RPG","SandBox","Action","Strategy","Puzzle"},
            {"Horror","Action","Romantic","Fantasy","Comedy"},
            {"Sci-Fi","Dukaj","Sapkowski","Epic"}
    };

    static  String[][][] child = {
            {
                    {"Baldurs Gate","Skyrim","The Witcher","Neverwinter Nights","Plansecape Torment"},
                    {"GTA","Red Dead Redemption","Dead Island"},
                    {"Call Of Duty","Mass Effect","Crysis","God Of War"},
                    {"Stronghold","TBOME","Warcraft","Napoleonic Wars","Majesty"},
                    {"Mario"}
            },
            {
                    {"Scary Movie","Dead Space","Who?","Dead Zone","Massacre"},
                    {"James Bond","Indiana Jones","Sherlock","300","Troya"},
                    {"50 Gray or sth","Simpsons","Dirty Dancing"},
                    {"Conan","LOTR","Witcher","Race with time"},
                    {"House MD","Dumb and dumber","And who is talking?"}
            },
            {
                    {"Mass Effect","Dynasty of Evil","Darth Bane","The Rule of Two"},
                    {"Black Oceans","Daugher of a plunderer","Other Songs","Extensa","Perfect imperfection"},
                    {"Baptism of fire","Tower of Swallow","Blood of elves","Sword of Destiny","Last wish"},
                    {"The Eye of th Moon","Book without title"}
            }
    };
}
