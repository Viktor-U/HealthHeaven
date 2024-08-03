import DoctorListItem from './doctor-list-item/DoctorListItem';
import useGetAllDoctors from '../../hooks/useDoctors';

export default function DoctorList(){

    
    const [doctors] = useGetAllDoctors();


    return(

        <section id="catalog-page">
            <h1>All Doctors</h1>

          {doctors.length > 0 
                ? doctors.map(doctor => <DoctorListItem key={doctor.id} {...doctor} />)
                : <h3 className="no-articles">No doctors on work yet</h3>
          }
        </section>
   
    );
}