docker build --no-cache -t openai_api_question_evaluation_app_img .

docker run -d -p 9090:9090 --name=openai_api_question_evaluation_app_con openai_api_question_evaluation_app_img