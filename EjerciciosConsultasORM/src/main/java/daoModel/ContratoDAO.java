package daoModel;

import connection.Connection;
import voModel.Contrato;

import javax.persistence.Query;

@SuppressWarnings("ALL")
public class ContratoDAO {

    public Query stablishConnection(String hql){
        Connection myConnection = new Connection();
        String queryHql = hql;
        Query query = myConnection.getConnection().createQuery(queryHql);
        myConnection.disconnect();
        return query;
    }

    /*1a) Actualizar la fecha de vencimiento de un contrato que introducimos como parámetro.*/
    public Contrato findContrato(String codContrato){
        String hql = "FROM Contratos c WHERE c.codContrato =" + codContrato;
        Contrato contrato = (Contrato) stablishConnection(hql).getSingleResult();
        return  contrato;
    }



}
