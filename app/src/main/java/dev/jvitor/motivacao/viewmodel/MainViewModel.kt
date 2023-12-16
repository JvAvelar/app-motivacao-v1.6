package dev.jvitor.motivacao.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.jvitor.motivacao.infra.Constants
import dev.jvitor.motivacao.infra.Phrase
import dev.jvitor.motivacao.infra.SecurityPreferences
import kotlin.random.Random

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val security = SecurityPreferences(application)
    private val name = MutableLiveData(getValueName())

    // Valores para selecionar as frases
    private val all = Constants.Names.ALL_INCLUSIVE
    private val happy = Constants.Names.HAPPY
    private val sunny = Constants.Names.SUNNY

    /* Função que está sendo observada ao ser alterada. Responsável por verificar e alterar
    o nome de usuário e por redirecionar para a tela secundária se o nome estiver vazio ao iniciar. */
    fun setName() : LiveData<String>{
        return name
    }
    // Função responsável por alterar a mensagem do nome
    fun message(name: String) : String{
        return "Olá, ${name}!"
    }

    // Função responsável por guardar os nomes de usuários
    private fun getValueName() : String{
        return security.getString(Constants.Names.USER_NAME)
    }

    // Lista de frases
    private val listPhrases = listOf(
        Phrase("Não sabendo que era impossível, foi lá e fez.", happy),
        Phrase("Você não é derrotado quando perde, você é derrotado quando desiste!", happy),
        Phrase("Quando está mais escuro, vemos mais estrelas!", happy),
        Phrase("Insanidade é fazer sempre a mesma coisa e esperar um resultado diferente.", happy),
        Phrase("Não pare quando estiver cansado, pare quando tiver terminado.", happy),
        Phrase("O que você pode fazer agora que tem o maior impacto sobre o seu sucesso?", happy),
        Phrase("A melhor maneira de prever o futuro é inventá-lo.", sunny),
        Phrase("Você perde todas as chances que você não aproveita.", sunny),
        Phrase("Fracasso é o condimento que dá sabor ao sucesso.", sunny),
        Phrase("Enquanto não estivermos comprometidos, haverá hesitação!", sunny),
        Phrase("Se você não sabe onde quer ir, qualquer caminho serve.", sunny),
        Phrase("Se você acredita, faz toda a diferença.", sunny),
        Phrase("Riscos devem ser corridos, porque o maior perigo é não arriscar nada!", sunny),
        Phrase("Vai! E se der medo, vai com medo mesmo.", sunny),
        Phrase("Resgate suas forças e se sinta bem!", sunny),
        Phrase("Para quem tem fé, a vida nunca tem fim.", sunny),
        Phrase("Acorde todas as manhãs com um sorriso. Esta é mais uma oportunidade que você tem para ser feliz. " +
                "Seja seu próprio motor de ignição. O dia de hoje jamais voltará... Não o desperdice!", happy),
        Phrase("Agradeço todos os dias pela minha vida, por tudo o que conquistei até aqui. " +
                "E tenho fé de que o futuro me reserva realizações ainda maiores!" , happy),
        Phrase("Só tenho a agradecer a cada um dos desafios que me trouxeram até aqui, que me fizeram crescer e aprender tantas coisas!", happy),
        Phrase("A gratidão é uma grande aliada do sucesso!", happy),
        Phrase("Só há felicidade se não exigirmos nada do amanhã e aceitarmos do hoje, com gratidão, o que nos trouxer. A hora mágica chega sempre.", happy),
        Phrase("Quando seu coração está pleno de gratidão, qualquer porta aparentemente fechada pode ser uma abertura para uma bênção maior.", happy),
        Phrase("Ser feliz sem motivo é a mais autêntica forma de felicidade.", happy),
        Phrase("Lute. Acredite. Conquiste. Perca. Deseje. Espere. Alcance. Invada. Caia. Seja tudo o quiser ser, mas, acima de tudo, seja você sempre.", sunny),
        Phrase("A gratidão é o único tesouro dos humildes.", sunny),
        Phrase("Você não precisa de platéia cheia para reconhecer que você é o melhor.", sunny),
        Phrase("Você tem que estudar para não ficar pagando de burro publicamente.", sunny),
        Phrase("Não acredito em sorte! tudo é determinação, ação e não procrastinação, nada é aleatório.", sunny),
        Phrase( "Crescer dói e dói muito, mas vale muito a pena!", sunny),
        Phrase("Não acredito que nada seja aleatório, ou em sorte. Acredito em Deus e em propósitos.", sunny),
        Phrase("Momentos Aleatórios podem ser os mais perfeitos. Aproveite-os!", sunny)

    )

    // Função responsável por pegar as frases
    fun getPhrases(value: Int) : String {
        val filtered = listPhrases.filter{ it.categoryId == value || value == all }
        return filtered[Random.nextInt(filtered.size)].description
    }
}
