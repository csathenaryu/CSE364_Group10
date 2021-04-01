# 프로젝트이름 (CSE364_Group10)
## Introduction
`프로젝트이름`은 **조건**에 맞는 영화를 추천해주는 `영화 추천 프로그램`입니다.  
영화의 `genres`와 user의 `occupation` 정보를 입력하시면, 해당 조건에 맞는 영화들의 평균 `rating`을 보실 수 있습니다.  
`프로젝트이름`을 통해 당신의 취향에 맞는 영화를 찾으세요!  
<br>
<br>
<br>



## Index
+ [Introduction](Introduction)
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
해당 repository를 다운받거나, 아래 커맨드를 실행하여 프로그램을 설치합니다.  
```
$ git init
$ git clone https://github.com/csathenaryu/CSE364_Group10.git
$ git pull origin main
$ mvn install
``` 
해당 repository에서 제공하는 `run.sh` 파일을 활용할 수도 있습니다.  
Prequisite: 프로그램 설치와 실행을 위해 `java` 11 이상, `git` and `maven`이 필요합니다.  
<br>
<br>
<br>



## Usage
`genres`와 `occupation`을 **순서대로 입력**하면, 조건에 맞는 영화의 평균 `rating` 결과가 출력됩니다.  
다음 명령어를 이용하여 프로그램을 실행할 수 있습니다.  
반드시 **입력 순서**를 지켜주세요.  
```
java -cp target/CSE364-project-1.0-SNAPSHOT.jar main.java.Main "gernre1|genre2|...|genreN" "occupation"
```  

예를 들어, `action`, `animation`, `romance` 장르에 모두 해당하는 영화에 대해 `educator` 직업을 가진 user가 평가한 결과가 궁금한 경우, 아래와 같이 입력합니다.  
```
java -cp target/CSE364-project-1.0-SNAPSHOT.jar main.java.Main action|animation|romance educator
```  
`genres`와 `occupation` 입력에 대한 상세 설명은 아래를 참고해주세요.   
<br>


### Genres  
- 검색 가능한 `genres`는 다음과 같습니다.   
`action`, `animation`, `children's`, `comedy`, `crime`,  `documentary`, `drama`, `fantasy`, `film-noir`, `horror`,  `musical`, `mystery`, `romance`, `scifi`, `thriller`, `war`, `western`  
    ```
    action            # action 장르 영화 검색
    ```
    ```
    thriller          # thriller 장르 영화 검색
    ```
    **대소문자 관계 없이** 검색 가능합니다.  
    ```
    Action            # action 장르 영화 검색
    ```
    ```
    thRILler          # thriller 장르 영화 검색
    ```
    **위 목록에 기술되지 않은 장르**를 입력할 경우 해당 영화 장르는 무시됩니다.
    ```
    adventure         # Error: InvalidValueError
    ```
    **철자**에 유의하여 작성해주세요.  
    아쉽게도 이 프로그램은 철자 자동 교정 기능을 제공하지 않습니다.  
    이 경우, 해당 장르의 입력은 무시됩니다.  
    ```
    actino            # Error: InvalidValueError
    ```

- 여러 장르를 입력하는 경우, delimiter `|`로 장르를 구분하여 입력해주세요.  
입력한 장르에 모두 해당되는 영화를 검색합니다.  
    ```
    action|thriller   # action, thriller에 모두 해당되는 영화 검색
    ```
    이 경우, 중간에 **띄어쓰기**가 들어가지 않도록 주의해주세요.   
    띄어쓰기가 포함된 경우 가장 앞의 입력만 반영되며, `occupation` 정보가 제대로 반영되지 않을 수 있습니다.  
    ```
    action|romance |thriller|war     # action, romance에 모두 해당되는 영화 검색, thriller, war는 검색되지 않음
    ```

- **위 목록에 기술되지 않은 장르**를 포함하여 입력할 경우 해당 영화 장르는 무시되며, 유효한 장르에 대해서만 검색합니다.  
    ```
    adventure|animation              # animation 장르 영화만 검색, adventure는 무시됨
    ```
    유효한 장르가 입력되지 않은 경우, 프로그램을 종료합니다.  
    ```
    adventure|animatino              # Error: InvalidValueError
    ```
- 장르를 **중복** 입력한 경우, 중복 입력은 무시합니다.  
    ```
    animation|animation|romance      # animation, romance 장르 영화 검색
    ```
<br>
<br>

### Occupation  
- 검색 가능한 `occupation`은 다음과 같습니다.  
`academic`, `educator`, `artist`, `clerical`, `admin`, `collegestudent`, `gradstudent`, `customerservice`, `doctor`,`healthcare`, `executive`, `managerial`, `farmer`, `homemaker`, `k-12student`, `lawyer`, `programmer`, `retired`, `sales`, `marketing`, `scientist`, `self-employed`, `technician`, `engineer`, `tradesman`, `craftsman`, `unemployed`, `writer`, `other`  
    ```
    artist          # artist 가 평가한 영화 검색
    ```
    ```
    farmer          # farmer 가 평가한 영화 검색
    ```
    **대소문자 관계 없이** 검색 가능합니다.  
    ```
    Artist          # artist 가 평가한 영화 검색
    ```
    ```
    faRMer          # farmer 가 평가한 영화 검색
    ```
    **위 목록에 기술되지 않은 직업**은 `other` 직업군으로 인식하여 검색을 진행합니다.   
    ```
    dentist         # other 직업군이 평가한 영화 검색
    ```
    **철자**에 유의하여 작성해주세요.  
    아쉽게도 이 프로그램은 철자 자동 교정 기능을 제공하지 않습니다.  
    이 경우, 철자 오류가 있는 직업은 `other` 직업군으로 인식됩니다.     
    ```
    actino      # Error: InvalidValueError
    ```
    
- **두 단어 이상**으로 이루어진 `occupation`의 경우, **띄어쓰기 없이** 하나로 붙여서 검색해주세요.  
    ```
    collegestudent  # collegestudent 가 평가한 영화 검색
    ```
    일부만 입력하는 경우, `other` 직업 군으로 검색을 진행합니다.  
    ```
    college         # other 가 평가한 영화 검색
    ```

- `genres`만 입력하고 **`occupation`을 입력하지 않은 경우**, 모든 `occupation`에 대한 결과를 계산합니다.  
    ```
    java -cp target/CSE364-project-1.0-SNAPSHOT.jar main.java.Main Action|Romance   # action, romance 장르 영화 검색, 모든 직업 군의 평가 정보 반영
    ```  
<br>
<br>

### Rating
- `rating`은 입력한 genres와 occupation에 해당하는 영화 rating의 `평균` 값입니다.  
- average rating은 소수점 셋째자리에서 반올림되어 제공됩니다.    
- 입력한 genres와 occupation에 해당하는 영화 rating 정보가 없는 경우, `No Information` 메시지가 출력됩니다.  
<br>
<br>
<br>

## Contributors
20171113 박소연  
20171155 안종민  
20171375 류성화  
20181072 김혜진
<br>
<br>
<br>
