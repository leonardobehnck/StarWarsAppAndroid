# StarWarsApp

Aplicativo Android desenvolvido em **Kotlin** como projeto de estudo, consumindo a [SWAPI (Star Wars API)](https://swapi.dev/) para listar e exibir detalhes de personagens do universo Star Wars. O app permite explorar personagens, visualizar seus atributos e salvar favoritos localmente com SQLite.

## Funcionalidades

- **Tela inicial com abas** — navegacao por tabs (ViewPager2 + TabLayout) com seções de conteúdo e contato do desenvolvedor
- **Seleção de personagens** — lista de personagens obtida da SWAPI exibida em um Spinner
- **Detalhes do personagem** — tela com atributos completos: nome, altura, peso, cor do cabelo, cor da pele, cor dos olhos, ano de nascimento, gênero, planeta natal, filmes, espécies, veículos e naves
- **Favoritos** — adicione personagens aos favoritos (persistidos em SQLite) e visualize a lista salva
- **Tratamento offline** — exibição de estado vazio com ícone e mensagem quando não há conexão com a internet
- **Último personagem visto** — o app salva via SharedPreferences o último personagem selecionado

## Tech Stack

| Camada | Tecnologia |
|---|---|
| Linguagem | Kotlin |
| UI | Android Views (XML) com ConstraintLayout |
| Navegação | ViewPager2 + TabLayout + Fragments |
| Rede | Retrofit 2 + Gson |
| Banco de dados local | SQLite (SQLiteOpenHelper) |
| Preferências | SharedPreferences |
| Design | Material Design Components |

## Arquitetura

O projeto segue uma organização em camadas:

```
com.app.starwarsapp/
├── data/                  # Camada de dados
│   ├── CharacterApi       # Interface Retrofit (SWAPI)
│   ├── CharacterWrapper   # DTO de resposta da API
│   └── local/
│       ├── CharacterContract    # Schema do banco SQLite
│       ├── CharacterDbHelper    # Helper do SQLite
│       └── CharacterRepository  # CRUD e lógica de favoritos
├── domain/                # Camada de domínio
│   └── Character          # Modelo de personagem
└── ui/                    # Camada de apresentação
    ├── adapter/
    │   └── TabsAdapter    # Adapter do ViewPager2
    ├── MainActivity       # Tela inicial com tabs
    ├── IndexFragment      # Fragment da aba principal
    ├── ContactFragment    # Fragment da aba de contato
    ├── Selector           # Tela de seleção de personagem
    ├── Person             # Tela de detalhes do personagem
    └── Favorite           # Tela de favoritos
```

## API utilizada

- **Base URL:** `https://swapi.dev/api/`
- **Endpoint:** `people/?format=json`
- Retorna uma lista paginada de personagens do universo Star Wars

## Configuração do projeto

| Propriedade | Valor |
|---|---|
| Min SDK | 21 (Android 5.0) |
| Target SDK | 34 (Android 14) |
| Compile SDK | 34 |
| Kotlin | 1.9.0 |
| Gradle | 8.9 |
| AGP | 8.7.2 |

## Como executar

1. Clone o repositório
2. Abra o projeto no **Android Studio**
3. Aguarde a sincronização do Gradle
4. Execute o app em um emulador ou dispositivo físico com Android 5.0+

## Aprendizados

Este projeto exercita os seguintes conceitos:

- Consumo de API REST com Retrofit e deserialização JSON com Gson
- Navegação com TabLayout, ViewPager2 e Fragments
- Persistência local com SQLite (sem Room)
- Verificação de conectividade de rede
- Uso de SharedPreferences para armazenar estado simples
- Manipulação de UI com ProgressBar e estados de carregamento
- Tratamento de estados vazios e offline
