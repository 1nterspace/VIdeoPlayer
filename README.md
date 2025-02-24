## Архитектура приложение 
![Архитектура с di (1)](https://github.com/user-attachments/assets/5ba45500-07bf-4c4b-a2f4-ec5b4a6955ed)

## Пример работы приложения: 
https://youtube.com/shorts/0p8XL2ZbMvo

## Стек:
- Архитектура - Clean architecture,MVVM
- DI - Koin
- UI - Activity,Fragment,RecyclerView,SwipeRefreshLayout
- Сеть - OkHttp,Retrofit
- Api - api.video
- MinSdk - Api26

## Документациия к api: 
- https://docs.api.video/vod?_gl=1*7y3e3u*_gcl_au*MTU5MzQ5Mzk2OS4xNzM5ODc0MTU4LjEzNzczNDE3OTMuMTczOTg3NDc5Ny4xNzM5ODc0ODMw*_ga*ODA3MDM5MzY4LjE3Mzk4NzQxNTg.*_ga_PSYKRMEWL4*MTc0MDQwODk0NS4yNy4wLjE3NDA0MDg5NDUuMC4wLjA.)

## 1.Экран со списком видео:
- На главном экране отображается весь список видео, который приходит с сервера
- Каждый элемент списка имеет thumbnail(миниатюра), название и продолжительность видео.
- Список загружаеться с api
- Также реализован SwipeRefreshLayout, который дает возможность потянуть вниз для обновления списка видео.



