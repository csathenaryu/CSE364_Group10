# VIM PROJECTOR (CSE364_Group10)

## Introduction
`VIM PROJECTOR` is a `movie recommendation program` which recommends movies according to given conditions.  
Type in the `genres` you wish to watch and your `occupation`, and you will recieve an `average rating` of the movies that belong to the given genres and were rated by others in your occupation.  
Find your next movie to watch with `VIM PROJECTOR`!!!  
<br>
<br>
<br>



## Index
+ [Introduction](#Introduction)
+ [Index](#Index)
+ [Installation](#Installation)
+ [Usage](#Usage)
    + [Genres](#Genres)
    + [Occupation](#Occupation)
    + [Rating](#Rating)
+ [Contributors](#Contributors)
<br>
<br>
<br>



## Installation
To install this program, download the repository directly, or run the commands below.  
```
$ git clone https://github.com/csathenaryu/CSE364_Group10.git
$ git pull origin main
$ mvn install
$ mvn assembly:assembly
``` 
This program can also be installed by running the `run.sh` file that is included in the repository.  
`Java(version 11 or up)`, `Git` and `Maven` installation must be preceded before installing and running this program.  
<br>
<br>
<br>



## Usage
Entering `genres` and `occupation` in order will give you the `average rating score` of the movies that correspond to the conditions you entered.  
Run this program by typing in the following command.
Please enter your conditions **in the given order.**  
  
```
$ java -cp target/CSE364-project-1.0-SNAPSHOT-jar-with-dependencies.jar main.java.Main "gernre1|genre2|...|genreN" "occupation"
```  
For example, if you wish to know the average rating score of the movies that belong to `action`, `animation` and `romance` and are rated by users with an occupation of `educator`, type command as below.  
```
$ java -cp target/CSE364-project-1.0-SNAPSHOT-jar-with-dependencies.jar main.java.Main "action|animation|romance" "educator"
```  
Note that a rating score of a movie will be incorporated in the result only if the movie belongs to all three genres.  
Please refer to the following sections about details in inputting `genres` and `occupation`.  
<br>
<br>


### Genres  
- The `genres` available are listed as the following:  
`action`, `animation`, `children's`, `comedy`, `crime`,  `documentary`, `drama`, `fantasy`, `film-noir`, `horror`,  `musical`, `mystery`, `romance`, `scifi`, `thriller`, `war`, `western`  
    ```
    action            # output: Your genres are [action]
    ```
    **Capitalization** does not matter.  
    ```
    thRILler          # output: Your genres are [thriller]
    ```
    **Genres not listed on the list above** will be ignored when entered.  
    ```
    adventure         # output: adventure is invalid or already exist. Try using another genres.
                      #         There is no valid input. Terminate program.
    ```
    Please **avoid spelling mistakes.**  
    This program does not provide any fool-proof feature for such mistakes.  
    In this case, the input will be ignored.  
 
    ```
    actino            # output: actino is invalid or already exist. Try using another genres.
                      #         There is no valid input. Terminate program.
    ```

- In case you are entering **several genres**, please distinguish each genre by delimiter `|`.  
The program will search for movies that match all genres given as input.  
    ```
    action|thriller   # output: Your genres are [action, thriller]
    ```
    Please **do not include spaces** in between genres.  
    If a space exists, only the inputs in front of the space will be applied, and `occupation` information may be omitted.  

    ```
    action|romance |thriller|war     # output: Your genres are [action, romance]
                                     # thriller, war are not included
    ```

- If a genre is not in the list above but is included in a list of genres given, the invalid genre will be ignored, and the program will search only for those available.  
    ```
    adventure|animation      # output: Your genres are [animation]
    ```
    If there is **no valid genre input**, the program will be terminated.  
    ```
    adventure|animatino      # output: adventure is invalid or already exist. Try using another genres.
                             #         animatino is invalid or already exist. Try using another genres.
                             #         There is no valid input. Terminate program.
    ```
- **Repeated inputs** will be ignored.  
    ```
    animation|animation|romance      # output: animation is invalid or already exist. Try using another genres.
                                     # Your genres are [animation, romance]
    ```
<br>

### Occupation  
- The `occupations` available are listed as the following:  
`academic`, `educator`, `artist`, `clerical`, `admin`, `collegestudent`, `gradstudent`, `customerservice`, `doctor`,`healthcare`, `executive`, `managerial`, `farmer`, `homemaker`, `k-12student`, `lawyer`, `programmer`, `retired`, `sales`, `marketing`, `scientist`, `self-employed`, `technician`, `engineer`, `tradesman`, `craftsman`, `unemployed`, `writer`, `other`  
    ```
    artist          # output: Your occupation code is [2]
    ```
    **Capitalization** does not matter.  
    ```
    faRMer             # output: Your occupation code is [8]
    ```
    **Occupation informations that are not listed in the list above** will be regarded as `other`.   
    ```
    dentist            # output: Your occupation code is [0]
                       # The occupation code of 'other' is 0
    ```
    Please **avoid spelling mistakes.**  
    This program does not provide any fool-proof feature for such mistakes.  
    In this case, the input will be regarded as `other`.  
  
    ```
    actino             # output: Your occupation code is [0]
    ```
    
- For `occupation` informations that consist of **more than one word**, omit the space in between and **input as one word.**  
    ```
    collegestudent     # output: Your occupation code is [4]
    ```
    In case only a part of occupation is entered, it will be regarded as `other`.     
    ```
    college            # output: Your occupation code is [0]
    ```

- If `genre(s)` has been entered and **`occupation` omitted**, the program will include movies rated by all `occupations`.  
    ```
    java -cp target/CSE364-project-1.0-SNAPSHOT-jar-with-dependencies.jar main.java.Main Action|Romance   # output: Your occupation code is [0, 1, 2, ..., 19, 20]
    ```  
<br>

### Rating
- `Rating` is an **average rating score** of the movies in given `genres` and rated by given `occupation`.  
- Rating will be printed out with a value rounded off to the nearest hundreadths.  
- If there is no movie rating information corresponding to given inputs, a message of `No Information` will be printed out.  
<br>
<br>
<br>



## Contributors
- 20171113 박소연
    1. Suggested algorithm utilizing boolean ArrayList
    2. Implemented code that receives boolean ArrayList as input and returns Rating as output

- 20171155 안종민
    1. Implemented code that processes parsed data as a boolean ArrayList
    2. Edited Pom.xml

- 20171375 류성화
    1. Implemented code that processes parsed data as a boolean ArrayList
    2. Translated README.md

- 20181072 김혜진
    1. Implemented code that receives and parses arguments as inputs
    2. Wrote first draft of README.md
<br>
<br>
<br>
