# VIM PROJECTOR (CSE364_Group10)

## Introduction - Milestone 2
`VIM PROJECTOR` is a `movie recommendation program` which recommends movies according to given conditions.  
Type in the `genres` you wish to watch and your `gender`, `age` and `occupation` than you will recieve an `10 recommended movies` of the movies that belong to the given genres.  
Find your next movie to watch with `VIM PROJECTOR`!!!
<br>
<br>
<br>

## Index
+ [Algorithm](#Algorithm)
+ [Introduction](#Introduction)
+ [Index](#Index)
+ [Installation](#Installation)
+ [Usage](#Usage)
    + [Common](#Common)
    + [Gender](#Gender)
    + [Age](#Age)
    + [Occupation](#Occupation)
    + [Genres](#Genres)
+ [Contributors](#Contributors)
<br>
<br>
<br>

## Algorithm
Our system uses two condition for good recommendation, `rating` and `count`.
`rating` is the average score of each movie that belongs to `genre` which is rated by user belongs to `gender`, `age`, and `occupation`.
`count` is the number of rating data corresponding to target users and movies which is entered by user.

First, we sort the entire target rating so that the high rating comes first, and find 10 of them to recommend.
At this, `rating` must be at least 4 points, and `count` must be at least 1% of the number of total rating data
that corresponds to `gender`, `age`, `occupation`, and `genre`.

If total number movies that satisfy condition is less than 10, the range or users will be expanded in order of
`occupation`, `age`, and `gender` to fill 10 movies.

For example, there is only one person in `male`, `56+`, `k-12 student` group. In this case, recommended movies are less than 10,
so the scope of user will be expanded to `male`, `56+`. If this is still not enough, look for data for `male` user, and then all users.
Finally, we recommend 10 movies by expanding the range to all users, even if the rating data of the target user is very little.
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
The program will recommend you the `10 movies` and links to imdb according to `gender`, `age`, `occupation` and `genres`.  
Run this program by typing in the following command.   
```
$ java -cp target/CSE364-project-1.0-SNAPSHOT-jar-with-dependencies.jar main.java.Milestone2 "GENDER" "AGE" "OCCUPATION" "GENRES"
```   

For example, let's see the sample command and recommended movie list.  
It is the `romance` movies for the `man` who is `60` and `retired`.
```
$ java -cp target/CSE364-project-1.0-SNAPSHOT-jar-with-dependencies.jar main.java.Milestone2 "M" "60" "Retired" "Romance"
```  
```
Before Sunrise (1995) (http://www.imdb.com/title/tt0112471) 
Much Ado About Nothing (1993) (http://www.imdb.com/title/tt0107616) 
Bound (1996) (http://www.imdb.com/title/tt0115736) 
Ninotchka (1939) (http://www.imdb.com/title/tt0031725) 
Farewell to Arms, A (1932) (http://www.imdb.com/title/tt0022879) 
Damsel in Distress, A (1937) (http://www.imdb.com/title/tt0028757) 
Diva (1981) (http://www.imdb.com/title/tt0082269) 
Wings (1927) (http://www.imdb.com/title/tt0018578) 
Say Anything... (1989) (http://www.imdb.com/title/tt0098258) 
Blame It on Rio (1984) (http://www.imdb.com/title/tt0086973)
```

Please refer to the following sections about details in inputting `gender`, `age`, `occupation` and `genres`.  
<br>
<br>


### Common
- Please enter `gender`, `age`, `occupation` and `genres` **in the given order**.  
    ```
    "M" "60" "Retired" "Romance"     # right input
    ```
    ```
    "M" "Retired" "60" "Romance"     # wrong input
    ```
- If you want to search movie regardless of particular fields, please leave the part empty.
    ```
    "" "" "Retired" "Romance"        # search for regardless of gender and age
    ```
- Please refer to `list of available input`. The input which is not in the list might be ignored or miscategorized.
- **Capitalization** does not matter.  
- Please **avoid spelling mistakes.** This program does not provide any fool-proof feature for such mistakes.  
- If there is **no valid input** in specific fields, the program will search movie regardless of the fields. 
<br>
<br>

### Gender
- **List of available input**: `m`, `f`
    ```
    "M"               # categorized as 'm'
    ```
- **Genders that are not in the list above** will be ignored.  
    ```
    "male"            # ignored
                      # output: Invalid gender 'male' will be ignored.
    ```
<br>
<br>


### Age
- **List of available input**: `0 or higher`
    ```
    "25"               # right input
    ```
    ```
    "0"                # right input
    ```
- **Ages that are not integer** will be ignored.  
    ```
    "-23"              # ignored
                       # output: Invalid age '-23' will be ignored
    ```
    ```             
    "60.3"             # ignored
                       # output: Invalid age '60.3' will be ignored
    ```
<br>
<br>


### Occupation  
- **List of available input**: `academic`, `educator`, `artist`, `clerical`, `admin`, `collegestudent`, `gradstudent`, `customerservice`, `doctor`,`healthcare`, `executive`, `managerial`, `farmer`, `homemaker`, `k-12student`, `lawyer`, `programmer`, `retired`, `sales`, `marketing`, `scientist`, `self-employed`, `technician`, `engineer`, `tradesman`, `craftsman`, `unemployed`, `writer`  
    ```
    "Academic"              # categorized as 'academic'
    ```
- **Occupations that are not in the list above** will be categorized as `other`.   
    ```
    "dancer"                # categorized as 'other'
                            # output: Invalid occupation 'dancer' will be categorized as 'Other'
    ```
- For an `occupation` that consists of **more than one word**, omit the space in between and **input as one word**.  
    If you put the space in between, it will be categorized as `other`.

    ```
    "college student"       # categorized as 'other'
                            # output: Invalid occupation 'college student' will be categorized as 'Other'
    ```
    If only a part of occupation is entered, it will be categorized as `other`.
    ```
    "student"               # categorized as 'other'
                            # output: Invalid occupation 'student' will be categorized as 'Other'
    ```
<br>
<br>


### Genres  
- **List of available input**: `action`, `adventure`, `animation`, `children's`, `comedy`, `crime`,  `documentary`, `drama`, `fantasy`, `film-noir`, `horror`,  `musical`, `mystery`, `romance`, `scifi`, `thriller`, `war`, `western`  
    ```
    "Action"                # search for 'action'
    ```
- **Genres that are not in the list above** will be ignored when entered.  
    ```
    "sports"                # ignored
                            # output: Invalid genres 'sports' will be ignored
    ```
- In case you are entering **several genres**, please distinguish each genre by delimiter `|`.  
    The program will search for movies that belong genres given as input.  
    ```
    "action|romance"        # search for `action` or 'romance'
    ```
- **Repeated inputs** will be ignored.  
    ```
    action|action|romance   # search for 'action' or 'romance'
    ```
- Please **do not include spaces** in between genres.  
    If a space exists, the genre will be ignored.
    ```
    "action| romance"       # search for 'action'
                            # output: Invalid genres ' romance' will be ignored
    ```
- If there is **no valid genre input**, the program will search movie regardless of genres.  
    ```
    "action |sports"        # search for regardless of genres
                            # output: Invalid genres ' action' will be ignored
                            #         Invalid genres 'sports' will be ignored
    ```
<br>
<br>
<br>



## Contributors
|Student ID|NAME|ROLE|
|:------|:-----|:--------|
|20171113|박소연|[Milestone 1]<br>- Suggested algorithm utilizing boolean ArrayList<br>- Implemented code that receives boolean ArrayList as input and returns Rating as output<br>[Milestone 2]<br>- Implemented 'recommender' package<br>- Implemented 'recommender' package test codes|
|20171155|안종민|[Milestone 1]<br>- Implemented code that processes parsed data as a boolean ArrayList<br>- Edited Pom.xml<br>[Milestone 2]<br>- Implemented 'parsinginputargs' package<br>- Implemented 'loadingdata' package test codes|
|20171375|류성화|[Milestone 1]<br>- Implemented code that processes parsed data as a boolean ArrayList<br> - Translated README.md<br>[Milestone 2]<br>- Implemented 'recommender' package<br>- Implemented 'Milestone2.java' test codes|
|20181072|김혜진|[Milestone 1]<br>- Implemented code that receives and parses arguments as inputs<br>- Wrote first draft of README.md<br>[Milestone 2]<br>- Implemented 'loadingdata' package<br>- Implemented 'customdatastructure' and 'parsinginputargs' package test codes|


<br>
<br>
<br>
