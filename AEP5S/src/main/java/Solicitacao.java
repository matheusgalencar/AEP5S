public class Solicitacao extends Usuario{
    private long id;
    private String descricao;
    private StatusSolicitacao status;

    public Solicitacao(String nome, String cpf, String email, String endereco, long id, String descricao, StatusSolicitacao status) {
        super(nome, cpf, email, endereco);
        this.id = id;
        this.descricao = descricao;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public StatusSolicitacao getStatus() {
        return status;
    }

    public void setStatus(StatusSolicitacao status) {
        this.status = status;
    }
}
