import DoctorListItem from './doctor-list-item/DoctorListItem';
import useGetAllDoctors from '../../hooks/useDoctors';
import { useTranslation } from 'react-i18next';

export default function DoctorList(){
    const [doctors] = useGetAllDoctors();
    const { t } = useTranslation();


    return(

        <section id="catalog-page">
            <h1>{t('all_doctors')}</h1>

            {doctors.length > 0 
                ? doctors.map(doctor => <DoctorListItem key={doctor.id} {...doctor} />)
                : <h3 className="no-articles">{t('no_doctors')}</h3>
            }
        </section>
   
    );
}