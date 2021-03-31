# CSE364_Group10
## Introduction
영화 `genres`와 user의 `occupation`을 입력하시면 rating 정보를 보여줍니다.  

For example  
`movie genres` - action, romance  
`user occupation` - educator  
`result rating` - 3.44  
<br>
<br>
<br>

## Getting Started
pre-requisite: `java11`
제공하는 run.sh 파일을 사용하셔도 좋습니다.  
run.sh 를 사용할 때는 `git`, `maven` 이 설치되어 있어야 합니다.  
run.sh 파일은 다음과 같습니다.  
```
rm -r testDir
mkdir testDir
cd testDir
git init
git clone https://github.com/csathenaryu/CSE364_Group10.git
git pull origin main
mvn install
```  
<br>
<br>
<br>

## 사용 방법
`genres`와 `occupation`을 순서대로 입력해주시면, 해당하는 영화의 평균 `rating` 결과를 출력합니다.  
해당 프로그램은 다음 명령어를 이용하여 실행할 수 있습니다.  
```
java -cp target/CSE364-project-1.0-SNAPSHOT.jar main.java.Main "gernre1|genre2|...|genreN" "occupation"
```  
<br>

### Genres  
- `genres`의 종류에는 이런 게 있습니다.  
`action`, `animation`, `children's`, `comedy`, `crime`,  `documentary`, `drama`, `fantasy`, `film-noir`, `horror`,  `musical`, `mystery`, `romance`, `scifi`, `thriller`, `war`, `western`  

- delimiter `|` 로 장르를 구분하여 입력하며, **대소문자는 구분되지 않습니다.**  
```
action|Romance|thRiLLer   # action, romance, thriller
```

- 중간에 **띄어쓰기**가 들어가는 경우, 가장 앞의 입력만 반영됩니다.  
다만 이러한 경우 `occupation` 정보가 제대로 반영되지 않습니다.  
```
action|romance |thriller|war     # action, romance
```

- **기술되지 않은 장르**를 입력할 경우, 해당 장르는 무시됩니다.  
```
adventure|animation    # animation (adventure: 장르 리스트에 존재x)
```

- **오타**도 무시됩니다.
```
123|,.]|한글|actino|romance     # romance
```

- 장르를 **중복**하여 입력하는 경우도 상관 없습니다?  
```
musical|musical|action     # musical, action
```

- **유효한 장르가 존재하지 않는 경우**, 에러가 발생합니다.
```
adventure   # Error: InvalidValueError
```
<br>
<br>

### Occupation  
- `occupation`의 종류에는 이런 게 있습니다.  
`other`, `academic`, `educator`, `artist`, `clerical`, `admin`, `collegestudent`, `gradstudent`, `customerservice`, `doctor`,`healthcare`, `executive`, `managerial`, `farmer`, `homemaker`, `k-12student`, `lawyer`, `programmer`, `retired`, `sales`, `marketing`, `scientist`, `self-employed`, `technician`, `engineer`, `tradesman`, `craftsman`, `unemployed`, `writer`  

- **대소문자는 구분되지 않습니다.**  
```
academic   # academic
```
```
self-employed   # self-employed
```

- **위 목록에 기술되지 않은 직업**을 입력할 경우, 해당 직업은 `other`로 tagging됩니다.    
```
dentist    # other
```

- **오타**가 있는 경우도 `other`로 tagging됩니다.  
```
hoemmaker     # other
```

- genres만 입력하고 **occupation을 입력하지 않은 경우**, 모든 occupation에 대한 결과를 계산합니다.  
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
