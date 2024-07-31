import DoctorListItem from './doctor-list-item/DoctorListItem';
import useGetAllDoctors from '../../hooks/useDoctors';

export default function DoctorList(){

    const [games] = useGetAllDoctors();


    return(

        <section id="catalog-page">
            <h1>All Doctors</h1>

          {games.length > 0 
                ? games.map(game => <DoctorListItem key={game.id} {...game} />)
                : <h3 className="no-articles">No doctors on work yet</h3>
          }
        </section>
   
    );
}