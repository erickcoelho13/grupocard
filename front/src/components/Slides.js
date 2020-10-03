import React, {useState, useEffect} from 'react';
import api from '../service/api';

function Slides({slides}) {
    const [count, setCount] = useState(0);
    let [jogador, setJogador] = useState("");
    let [jogada, setJogada] = useState("");
    let [resultado, setResultado] = useState("");
    let [inter, setInter] = useState(0);  

    function jogar(jogada){
        setJogada(jogada);
        api.post('/jogadas/' + jogada + '/' + jogador).then(response => {
            console.log(response.data);
        });
        
        setInter(setInterval(consultarResultado, 3000));
    }

    useEffect(() => {
        clearInterval(inter);
    }, [resultado]);

    function consultarResultado() {
        api.get('/jogadas/resultado').then(response => {
            console.log('Vencedor:' + response.data);
            if (response.data.status == 'Finalizada')
                setResultado(response.data.vencedor + '');
        });
    }

    return (
        <div>
            <div id="jogador" className="text-center" >
                <input placeholder="Seu Nome" value={jogador} onChange={e => setJogador(e.target.value)} />
            </div>
            <div id="navigation" className="text-center">
                <button data-testid="button-restart" className="small" name="pedra" onClick={(e) => jogar("pedra")}>Pedra</button>
                <button data-testid="button-prev" className="small" name="papel" onClick={(e) => jogar("papel")}>Papel</button>
                <button data-testid="button-next" className="small" name="tesoura" onClick={(e) => jogar("tesoura")}>Tesoura</button>
            </div>
            <div id="slide" className="card text-center">
                <p data-testid="text">Iniciando jogo...</p>
                {jogada && <h1 data-testid="title">Jogada: {jogada}</h1>}
                {resultado && <h1 data-testid="title">Vencedor: {resultado}</h1>}
            </div>
        </div>
    );

}

export default Slides;
