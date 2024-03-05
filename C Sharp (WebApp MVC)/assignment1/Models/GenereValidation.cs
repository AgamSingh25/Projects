using System.ComponentModel.DataAnnotations;


namespace assignment1.Models
{
    public class GenereValidation : ValidationAttribute
    {
        public override bool IsValid(object? value)
        {
            var genre = Convert.ToString(value).ToLower();
            bool result = false;
            if(genre == "action" || genre == "simulation" || genre == "puzzle")
            {
                result = true;
            }

            return result;
        }
    }
}
