using System.ComponentModel.DataAnnotations;

namespace assignment1.Models
{
    public class ReleaseYearValidation : ValidationAttribute
    {
        public override bool IsValid(object? value)
        {
            var releaseYear = Convert.ToInt32(value);
            bool result = false;
            if ((DateTime.Now.Year - releaseYear) >= 3)
            {
                result = true;
            }
            return result;
        }
    }
}
