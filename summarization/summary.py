import openai

# OpenAI API 키 설정

openai.api_key = ''   #보안 문제로, api key는 따로 보관 

def summarize_korean_text(text):
    """
    한국어 텍스트를 요약합니다.
    :param text: 요약할 한국어 텍스트
    :return: 요약된 텍스트
    """

    try:
        # 챗 모델을 위한 API 호출
        response = openai.ChatCompletion.create(
            model="gpt-3.5-turbo",  # 적절한 모델명으로 수정
            messages=[
                {"role": "system", "content": "다음 글을 3문장으로 요약해줘. 공백 포함 200글자 내외로 써줘."},
                {"role": "user", "content": text}
            ],
            max_tokens=300,  # 최소 토큰 수로 설정하여 크레딧 절약
            temperature=0.5,  # 낮은 다양성으로 더 정확한 요약 추구
            stop=["\n"]  # 문장 종료 조건
        )
        # 요약된 텍스트 반환
        if response['choices']:
            return response['choices'][0]['message']['content'].strip()
        else:
            return "요약을 생성하지 못했습니다."
    except Exception as e:
        return f"An error occurred: {str(e)}"


# 예제 텍스트
example_text = """
한국에서 출생했으나 협약에 따라 중국으로 보내진 자이언트 판다 푸바오의 현지 근황을 전하는 ‘사생팬’이 등장했다. 사생팬은 유명인의 사생활까지 쫓는 열혈 팬을 뜻한다.

15일 온라인에 따르면 최근 푸바오 사생팬 A씨가 운영하는 인스타그램 계정이 만들어졌다. 계정에는 푸바오가 지내고 있는 중국 쓰촨성 워룽 선수핑기지를 인근에서 몰래 촬영한 것으로 보이는 사진과 영상 등이 다수 업로드돼 있다. 맞은편 뒷산에서 찍은 것으로 추정되는 영상도 있다.

중국인인 A씨는 영상을 통해 사육사가 푸바오에게 사과는 몇 번 줬는지, 배변은 했는지, 학대를 당하진 않는지 등을 확인한다. 그는 전날 “(푸바오는) 오늘도 운동장에 나오지 않았다”며 짧은 영상을 공유하기도 했다. 푸바오는 한 달간 격리 및 검역 절차를 거쳐 외부에 공개될 것으로 전해졌다.

해당 계정에 대한 반응은 다소 엇갈리는 분위기다. “중국 팬들 대단하다” “광기의 중국 팬들”이라며 푸바오 소식을 반기는 반응이 많은 반면 일각에서는 중국 사육사를 감시하는 것 아니냐는 지적도 제기됐다. 논란이 일자 운영자는 해명에 나섰다. A씨는 해당 영상물을 직접 촬영한 것이 아니라 중국 SNS에 올라온 푸바오 소식을 대신 전하는 것이라고 했다. 실제로 푸바오의 일부 중국 현지 팬들은 선수핑기지 인근 산에 올라가 내부를 살피기도 하는 것으로 알려졌다.

A씨는 “푸바오에 대한 중국 사진과 영상 출처는 다양한 중국 SNS에서 모은 것”이라며 “그중 중국 팬의 캡처와 영상, 감시하는 듯한 각도의 사진이나 영상이 논란이 된 것”이라고 해명했다.

푸바오에 대한 남다른 애정을 고백하기도 했다. 그는 “지난해 3월부터 바오 가족을 좋아해 외국인으로서 말은 잘 통하지 않지만 한 달에 5시간씩 걸려 에버랜드에 바오 가족을 보러 갔다”고 전했다.

이어 “(나는) 오랫동안 한국에 살고 있어서 푸바오가 떠난 뒤 엄청 슬프고 힘들다”며 “한국 이모들이 푸바오를 걱정하는 마음을 이해하고 공감할 수 있다. 푸바오와 관련된 소식이 있을 때 바로 한국 이모들에게 알려드리고 싶었을 뿐”이라며 채널 운영 이유를 설명했다.

A씨는 “많은 분이 제 SNS 페이지를 캡처해 공격하고 있다. 마음이 너무 힘들었다”면서 “사육사가 푸바오를 정성껏 돌보는 모습을 공유해 한국 팬들을 안심시키고 싶은 것뿐이다. 사육사를 존경하고 매우 고생한다는 것을 알고 있다. 저를 공격하지 않았으면 좋겠다”고 당부했다. 푸바오는 2016년 3월 시진핑 중국 국가주석이 한·중 친선 도모의 상징으로 보내온 판다 러바오와 아이바오 사이에서 2020년 7월 20일 태어났다. 출생 1354일 만인 지난 3일 한국을 떠나 중국 선수핑기지로 옮겨졌다. 현지 환경에 순조롭게 적응 중인 것으로 전해졌다.

푸바오가 빠르게 적응한다면 일반 공개 시기가 앞당겨질 가능성도 있다. 통상 중국으로 반환된 판다가 관람객에게 공개되기까지는 판다의 적응 상황에 따라 짧게는 1~2개월, 길게는 7~8개월 걸리는 것으로 알려졌다.

푸바오는 한 달가량 격리와 검역 절차를 마친 이후 거취가 정해질 전망이다. 워룽 선수핑기지에 계속 머무를 수도 있고 워룽 허타오핑기지나 두장옌기지, 야안기지 세 곳 중 한 곳으로 이동할 수도 있다.
"""

# 함수 호출 및 요약 결과 출력
summary = summarize_korean_text(example_text)

#문장 분리
sentences = summary.split('. ')
sentence1 = sentences[0] + '.'
sentence2 = sentences[1] + '.'
sentence3 = sentences[2]


# print("원본: ", example_text)
print("\n\n\n요약된 텍스트\n")
print(sentence1)
print(sentence2)
print(sentence3)
